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

    // --------------------------------------------------
    // HELPERS
    // --------------------------------------------------

    private Loan getLoanOrThrow(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }
}
