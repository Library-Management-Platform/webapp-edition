package com.platform.libraryManager.logic.services;

import com.platform.libraryManager.dataAccess.models.*;
import com.platform.libraryManager.shared.enums.LoanStatusEnum;
import com.platform.libraryManager.shared.enums.ResourceCategoryEnum;
import com.platform.libraryManager.shared.enums.ResourceStatusEnum;
import com.platform.libraryManager.dataAccess.repositories.LoanRepository;
import com.platform.libraryManager.dataAccess.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanService {

    private final ResourceRepository resourceRepository;
    private final LoanRepository loanRepository;
    private final NotificationService notificationService;

    public LoanService(
            LoanRepository loanRepository,
            ResourceRepository resourceRepository,
            NotificationService notificationService) {
        this.loanRepository = loanRepository;
        this.resourceRepository = resourceRepository;
        this.notificationService = notificationService;
    }

    /**
     * Client reserves a resource if it is available
     */
    public Loan reserveResource(Client client, Resource resource, Library library) {
        // Check if the client already has a reservation for this resource
        boolean alreadyReserved = loanRepository.findByClientAndResourceAndStatus(
                client, resource, LoanStatusEnum.RESERVED).isPresent();
        if (alreadyReserved) {
            throw new IllegalStateException("You have already reserved this resource.");
        }

        // Create new reservation
        Loan loan = new Loan(client, resource, library);
        loan.setStatus(LoanStatusEnum.RESERVED);
        Loan savedLoan = loanRepository.save(loan);

        // Only mark the resource as RESERVED if currently AVAILABLE
        if (resource.getStatus() == ResourceStatusEnum.AVAILABLE) {
            resource.setStatus(ResourceStatusEnum.RESERVED);
            resourceRepository.save(resource);
        }

        return savedLoan;
    }

    @Transactional(readOnly = true)
    public List<Loan> getLoansForClient(Client client) {
        return loanRepository.findByClient(client);
    }

    // --------------------------------------------------
    // LIBRARIAN ACTIONS
    // --------------------------------------------------
    @Transactional(readOnly = true)
    public List<Loan> getActiveLoansForLibrary(Library library) {
        return loanRepository.findByLibraryAndStatusIn(
                library,
                List.of(
                        LoanStatusEnum.RESERVED,
                        LoanStatusEnum.BORROWED,
                        LoanStatusEnum.IN_PROGRESS));
    }

    public Loan borrowResource(Long loanId, Librarian librarian, int loanDurationDays) {
        Loan loan = getLoanOrThrow(loanId);

        if (!loan.getLibrary().equals(librarian.getLibrary())) {
            throw new SecurityException("Librarian cannot manage loans from another library");
        }

        loan.markAsBorrowed(loanDurationDays);

        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.BORROWED);
        resourceRepository.save(resource);

        return loanRepository.save(loan);
    }

    public Loan adminBorrowResource(Long loanId, int loanDurationDays) {
        Loan loan = getLoanOrThrow(loanId);
        loan.markAsBorrowed(loanDurationDays);

        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.BORROWED);
        resourceRepository.save(resource);

        return loanRepository.save(loan);
    }

    /**
     * Librarian marks resource as returned
     */
    public Loan returnResource(Long loanId, Librarian librarian) {
        Loan loan = getLoanOrThrow(loanId);

        if (!loan.getLibrary().equals(librarian.getLibrary())) {
            throw new SecurityException("Librarian cannot manage loans from another library");
        }

        return handleReturnAndNotify(loan);
    }

    /**
     * Admin-level return
     */
    public Loan adminReturnResource(Long loanId) {
        Loan loan = getLoanOrThrow(loanId);
        return handleReturnAndNotify(loan);
    }

    // --------------------------------------------------
    // CLIENT FEEDBACK / CLOSURE
    // --------------------------------------------------

    public Loan closeLoan(Long loanId, Client client, Integer rating, String comment) {
        Loan loan = getLoanOrThrow(loanId);

        if (!loan.getClient().equals(client)) {
            throw new SecurityException("Client cannot close another user's loan");
        }

        loan.closeLoan(rating, comment);
        return loanRepository.save(loan);
    }

    // --------------------------------------------------
    // ADMIN / SYSTEM QUERIES
    // --------------------------------------------------

    @Transactional(readOnly = true)
    public List<Loan> getAllActiveLoans() {
        return loanRepository.findAllActiveLoans();
    }

    @Transactional(readOnly = true)
    public List<Loan> getOverdueLoans() {
        return loanRepository.findOverdueLoans();
    }

    public double computeTurnoverRate() {
    // Numérateur : Combien de livres uniques ont déjà été prêtés ?
    long engagedResources = loanRepository.countDistinctResourcesInLoans();

    // Dénominateur : Combien de livres y a-t-il au total dans la collection ?
    long totalResources = resourceRepository.count();

    if (totalResources == 0) {
        return 0.0; 
    }

    // Le calcul : (livres utilisés / livres totaux) * 100
    return ((double) engagedResources / totalResources) * 100.0;
}

    // --------------------------------------------------
    // INTERNAL HELPERS
    // --------------------------------------------------

    private Loan getLoanOrThrow(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }

    /**
     * Shared return logic + availability notification
     */
    private Loan handleReturnAndNotify(Loan loan) {

        loan.markAsReturned();

        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.AVAILABLE);
        resourceRepository.save(resource);

        Loan savedLoan = loanRepository.save(loan);

        // Notify next reserved client (if any) only if not notified
        loanRepository.findFirstByResourceAndStatusAndAvailabilityNotifiedFalse(
                resource,
                LoanStatusEnum.RESERVED).ifPresent(reservedLoan -> {
                    notificationService.notifyAvailability(reservedLoan.getClient(), resource);

                    reservedLoan.setAvailabilityNotified(true);
                    loanRepository.save(reservedLoan);
                });

        return savedLoan;
    }

    @Transactional(readOnly = true)
    public List<Loan> getAllActiveLoansWithRelations() {
        return loanRepository.findAllActiveLoansWithRelations();
    }

    /**
     * Used by ADMIN views & overdue notifier
     * Fetches overdue loans with all required relations eagerly loaded
     */
    @Transactional(readOnly = true)
    public List<Loan> getOverdueLoansWithRelations() {
        return loanRepository.findOverdueLoansWithRelations();
    }

    @Transactional(readOnly = true)
    public Map<String, Long> countLoansByCategory() {
        return loanRepository.countLoansByCategoryName().stream()
                .collect(Collectors.toMap(
                        // Solution : Utilisez le nom complet et correct de votre classe Enum
                        row -> ((ResourceCategoryEnum) row[0]).name(),
                        row -> (Long) row[1]));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> countLoansByLibrary() {
        return loanRepository.countLoansByLibraryName().stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],
                        row -> (Long) row[1]));
    }

    // Ajoutez aussi cette méthode pour l'export CSV, c'est plus propre
    @Transactional(readOnly = true)
    public long countTotalLoans() {
        return loanRepository.count();
    }

}
