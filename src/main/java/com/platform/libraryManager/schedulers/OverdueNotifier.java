package com.platform.libraryManager.schedulers;

import com.platform.libraryManager.enums.LoanStatusEnum;
import com.platform.libraryManager.enums.NotificationTypeEnum;
import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.repositories.LoanRepository;
import com.platform.libraryManager.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OverdueNotifier {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 8 * * ?")
    @Transactional
    public void notifyOverdueLoans() {

        List<Loan> overdueLoans = loanRepository.findByStatusAndDueDateBefore(
                LoanStatusEnum.IN_PROGRESS,
                LocalDateTime.now());

        for (Loan loan : overdueLoans) {

            String message = "Retard détecté : le livre \"" +
                    loan.getResource().getTitle() +
                    "\" emprunté par " +
                    loan.getClient().getUsername() +
                    " est en retard.";

            for (Librarian librarian : loan.getLibrary().getLibrarians()) {

                notificationService.notifyUser(
                        librarian,
                        message,
                        NotificationTypeEnum.OVERDUE,
                        true);
            }
        }
    }
}
