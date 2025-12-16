/* package com.platform.libraryManager.config;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.repositories.ClientRepository;
import com.platform.libraryManager.repositories.LibraryRepository;
import com.platform.libraryManager.repositories.LoanRepository;
import com.platform.libraryManager.repositories.ResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OverdueLoanSeeder implements CommandLineRunner {

    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final ResourceRepository resourceRepository;
    private final LibraryRepository libraryRepository;

    public OverdueLoanSeeder(
            LoanRepository loanRepository,
            ClientRepository clientRepository,
            ResourceRepository resourceRepository,
            LibraryRepository libraryRepository
    ) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.resourceRepository = resourceRepository;
        this.libraryRepository = libraryRepository;
    }

    @Override
    public void run(String... args) {

        Client client = clientRepository.findById(28L).orElseThrow();
        Resource resource = resourceRepository.findById(213L).orElseThrow();
        Library library = libraryRepository.findById(24L).orElseThrow();

        // 1️⃣ Create RESERVED loan (constructor does this)
        Loan overdueLoan = new Loan(client, resource, library);

        overdueLoan.setAvailabilityNotified(false);

        // 2️⃣ Borrow it PROPERLY (this sets status = IN_PROGRESS)
        overdueLoan.markAsBorrowed(1);

        // 3️⃣ Force overdue dates (DEV ONLY)
        overdueLoanRepositoryHack(overdueLoan);

        loanRepository.save(overdueLoan);

        System.out.println("✅ Overdue loan seeded for notification testing");
    }

    private void overdueLoanRepositoryHack(Loan loan) {
        try {
            var dueField = Loan.class.getDeclaredField("dueDate");
            dueField.setAccessible(true);
            dueField.set(loan, LocalDateTime.now().minusDays(2));

            var borrowField = Loan.class.getDeclaredField("borrowDate");
            borrowField.setAccessible(true);
            borrowField.set(loan, LocalDateTime.now().minusDays(5));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
 */