package com.platform.libraryManager.services;

import com.platform.libraryManager.enums.LoanStatusEnum;
import com.platform.libraryManager.enums.ResourceStatusEnum;
import com.platform.libraryManager.models.*;
import com.platform.libraryManager.repositories.LoanRepository;
import com.platform.libraryManager.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanService {

    private final ResourceRepository resourceRepository;

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository, ResourceRepository resourceRepository) {
        this.loanRepository = loanRepository;
        this.resourceRepository = resourceRepository;
    }

    // --------------------------------------------------
    // CLIENT ACTIONS
    // --------------------------------------------------

    /**
     * Client reserves a resource if it is available
     */
   public Loan reserveResource(Client client, Resource resource, Library library) {

    // Check resource availability
    Optional<Loan> existingLoan = loanRepository.findFirstByResourceAndStatusIn(
            resource,
            List.of(
                    LoanStatusEnum.RESERVED,
                    LoanStatusEnum.BORROWED,
                    LoanStatusEnum.IN_PROGRESS
            )
    );

    if (existingLoan.isPresent()) {
        throw new IllegalStateException("Resource is not available for reservation");
    }

    // Create the loan
    Loan loan = new Loan(client, resource, library);
    loan.setStatus(LoanStatusEnum.RESERVED); // optional if Loan constructor doesn't already set it
    Loan savedLoan = loanRepository.save(loan);

    // Update the resource status to RESERVED
    resource.setStatus(ResourceStatusEnum.RESERVED); // assuming status is a String; otherwise use enum
    resourceRepository.save(resource);

    return savedLoan;
}


    /**
     * Client views their own loans
     */
    @Transactional(readOnly = true)
    public List<Loan> getLoansForClient(Client client) {
        return loanRepository.findByClient(client);
    }

    // --------------------------------------------------
    // LIBRARIAN ACTIONS
    // --------------------------------------------------

    /**
     * Librarian validates borrowing for a reservation
     */
    public Loan borrowResource(Long loanId, Librarian librarian, int loanDurationDays) {
        Loan loan = getLoanOrThrow(loanId);

        // Ownership check
        if (!loan.getLibrary().equals(librarian.getLibrary())) {
            throw new SecurityException("Librarian cannot manage loans from another library");
        }

        loan.markAsBorrowed(loanDurationDays);

        // Update the resource status to BORROWED
        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.BORROWED);
        resourceRepository.save(resource);

        return loanRepository.save(loan);
    }

    /**
     * Admin-level borrow: allow admin to mark a reserved loan as borrowed without librarian ownership checks
     */
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

        // Ownership check
        if (!loan.getLibrary().equals(librarian.getLibrary())) {
            throw new SecurityException("Librarian cannot manage loans from another library");
        }

        loan.markAsReturned();

        // Update the resource status to AVAILABLE
        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.AVAILABLE);
        resourceRepository.save(resource);

        return loanRepository.save(loan);
    }

    /**
     * Admin-level return: allow admin to mark an in-progress loan as returned without librarian ownership checks
     */
    public Loan adminReturnResource(Long loanId) {
        Loan loan = getLoanOrThrow(loanId);
        loan.markAsReturned();

        Resource resource = loan.getResource();
        resource.setStatus(ResourceStatusEnum.AVAILABLE);
        resourceRepository.save(resource);

        return loanRepository.save(loan);
    }

    /**
     * Librarian dashboard: active loans in their library
     */
    @Transactional(readOnly = true)
    public List<Loan> getActiveLoansForLibrary(Library library) {
        return loanRepository.findByLibraryAndStatusIn(
                library,
                List.of(
                        LoanStatusEnum.RESERVED,
                        LoanStatusEnum.BORROWED,
                        LoanStatusEnum.IN_PROGRESS
                )
        );
    }

    // --------------------------------------------------
    // CLIENT FEEDBACK / CLOSURE
    // --------------------------------------------------

    /**
     * Client closes loan with feedback
     */
    public Loan closeLoan(Long loanId, Client client, Integer rating, String comment) {
        Loan loan = getLoanOrThrow(loanId);

        // Ownership check
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

    // Analytics helpers for admin dashboard
    public Map<String, Long> countLoansByCategory() {
        return loanRepository.findAll().stream()
                .filter(l -> l.getResource() != null && l.getResource().getCategory() != null)
                .collect(Collectors.groupingBy(l -> l.getResource().getCategory().name(), Collectors.counting()));
    }

    public Map<String, Long> countLoansByLibrary() {
        return loanRepository.findAll().stream()
                .filter(l -> l.getLibrary() != null)
                .collect(Collectors.groupingBy(l -> l.getLibrary().getName(), Collectors.counting()));
    }

    public double computeTurnoverRate() {
        long totalLoans = loanRepository.count();
        long totalResources = resourceRepository.count();
        if (totalResources == 0) return 0.0;
        return ((double) totalLoans / (double) totalResources) * 100.0; // percentage
    }

    // --------------------------------------------------
    // HELPERS
    // --------------------------------------------------

    private Loan getLoanOrThrow(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }
}
