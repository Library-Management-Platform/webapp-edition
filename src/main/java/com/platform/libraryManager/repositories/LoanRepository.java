package com.platform.libraryManager.repositories;
import com.platform.libraryManager.enums.LoanStatusEnum;
import com.platform.libraryManager.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;


public interface LoanRepository extends JpaRepository<Loan, Long> {

// ---- Client scope ----

List<Loan> findByClient(Client client);
List<Loan> findByClientAndStatusIn(Client client, List<LoanStatusEnum> statuses);

// ---- Library scope (for librarians) ----


List<Loan> findByLibrary(Library library);


List<Loan> findByLibraryAndStatusIn(Library library, List<LoanStatusEnum> statuses);

// ---- Resource availability ----

Optional<Loan> findFirstByResourceAndStatusIn(
Resource resource,
List<LoanStatusEnum> statuses
);

// ---- Active loans ----

@Query("SELECT l FROM Loan l WHERE l.status <> com.platform.libraryManager.enums.LoanStatusEnum.CLOSED")
List<Loan> findAllActiveLoans();

// ---- Overdue loans ----

@Query("SELECT l FROM Loan l WHERE l.status = com.platform.libraryManager.enums.LoanStatusEnum.IN_PROGRESS AND l.dueDate < CURRENT_TIMESTAMP")
List<Loan> findOverdueLoans();
}