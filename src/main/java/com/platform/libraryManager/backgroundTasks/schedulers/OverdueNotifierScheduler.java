package com.platform.libraryManager.backgroundTasks.schedulers;

import com.platform.libraryManager.dataAccess.enums.LoanStatusEnum;
import com.platform.libraryManager.dataAccess.enums.NotificationTypeEnum;
import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Loan;
import com.platform.libraryManager.dataAccess.repositories.LoanRepository;
import com.platform.libraryManager.logic.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OverdueNotifierScheduler {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * Runs every day at 08:00
     */
    // @Scheduled(cron = "0 * * * * ?") // every minute
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void notifyOverdueLoans() {

        List<Loan> overdueLoans =
                loanRepository.findByStatusAndDueDateBefore(
                        LoanStatusEnum.IN_PROGRESS,
                        LocalDateTime.now()
                );

        for (Loan loan : overdueLoans) {

            String message =
                    "Retard détecté : le livre \"" +
                    loan.getResource().getTitle() +
                    "\" emprunté par " +
                    loan.getClient().getUsername() +
                    " est en retard.";

            // Notify ALL librarians of the library
            for (Librarian librarian : loan.getLibrary().getLibrarians()) {

                notificationService.notifyUser(
                        librarian, // Librarian IS a User
                        message,
                        NotificationTypeEnum.OVERDUE,
                        true
                );
            }
        }
    }
}
