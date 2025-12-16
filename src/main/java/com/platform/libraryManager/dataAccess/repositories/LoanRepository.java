package com.platform.libraryManager.dataAccess.repositories;

import com.platform.libraryManager.dataAccess.models.Client;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dataAccess.models.Loan;
import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dataAccess.enums.LoanStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // ---- Client scope ----

    List<Loan> findByClient(Client client);

    List<Loan> findByClientAndStatusIn(Client client, List<LoanStatusEnum> statuses);

    Optional<Loan> findByClientAndResourceAndStatus(
            Client client,
            Resource resource,
            LoanStatusEnum status);

    // Find first reserved loan for a resource that has not been notified
    Optional<Loan> findFirstByResourceAndStatusAndAvailabilityNotifiedFalse(
            Resource resource,
            LoanStatusEnum status);

    // ---- Library scope ----

    List<Loan> findByLibrary(Library library);

    List<Loan> findByLibraryAndStatusIn(Library library, List<LoanStatusEnum> statuses);

    // ---- Resource availability ----

    Optional<Loan> findFirstByResourceAndStatusIn(
            Resource resource,
            List<LoanStatusEnum> statuses);

    // ---- Simple overdue (used by scheduler) ----

    List<Loan> findByStatusAndDueDateBefore(
            LoanStatusEnum status,
            LocalDateTime dateTime);

    // ---- Active loans (NO relations) ----

    @Query("""
                SELECT l FROM Loan l
                WHERE l.status <> com.platform.libraryManager.dataAccess.enums.LoanStatusEnum.CLOSED
            """)
    List<Loan> findAllActiveLoans();

    // ---- Active loans WITH relations (FOR VIEWS) ----

    @Query("""
                SELECT l FROM Loan l
                JOIN FETCH l.client
                JOIN FETCH l.resource
                JOIN FETCH l.library
                WHERE l.status <> com.platform.libraryManager.dataAccess.enums.LoanStatusEnum.CLOSED
            """)
    List<Loan> findAllActiveLoansWithRelations();

    // ---- Overdue loans WITH relations (FOR VIEWS & NOTIFICATIONS) ----

    @Query("""
                SELECT l FROM Loan l
                JOIN FETCH l.client
                JOIN FETCH l.resource
                JOIN FETCH l.library
                WHERE l.status = com.platform.libraryManager.dataAccess.enums.LoanStatusEnum.IN_PROGRESS
                  AND l.dueDate < CURRENT_TIMESTAMP
            """)
    List<Loan> findOverdueLoansWithRelations();

    @Query("SELECT l FROM Loan l WHERE l.status = com.platform.libraryManager.dataAccess.enums.LoanStatusEnum.IN_PROGRESS AND l.dueDate < CURRENT_TIMESTAMP")
    List<Loan> findOverdueLoans();

}
