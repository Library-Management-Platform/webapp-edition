package com.platform.libraryManager.services;

import com.platform.libraryManager.enums.NotificationTypeEnum;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.Notification;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.models.User;
import com.platform.libraryManager.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class NotificationService {

        @Autowired
        private NotificationRepository notificationRepository;

        @Autowired
        private SimpMessagingTemplate messagingTemplate;

        @Autowired
        private EmailService emailService;

        @Transactional
        public void notifyUser(
                        User user,
                        String message,
                        NotificationTypeEnum type,
                        boolean sendEmail) {

                Notification notification = new Notification(
                                message,
                                LocalDateTime.now(),
                                type,
                                user);

                notificationRepository.save(notification);

                // WebSocket push notification
                messagingTemplate.convertAndSendToUser(
                                user.getUsername(),
                                "/queue/notifications",
                                Map.of(
                                                "message", message,
                                                "type", type.name(),
                                                "sentAt", notification.getDate().toString()));

                // email
                if (sendEmail && user.getEmail() != null) {
                        emailService.sendEmail(user.getEmail(), "Notification – Bibliothèque", message);
                }
        }

        /**
         * Notify a client that a reserved resource is now available
         */
        public void notifyAvailability(Client client, Resource resource) {

                String message = "Votre livre \"" +
                                resource.getTitle() +
                                "\" est prêt à être emprunté.";

                notifyUser(
                                client,
                                message,
                                NotificationTypeEnum.AVAILABILITY,
                                true);
        }
}
