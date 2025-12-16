package com.platform.libraryManager.ui.controllers;

import com.platform.libraryManager.dataAccess.models.Notification;
import com.platform.libraryManager.dataAccess.models.User;
import com.platform.libraryManager.dataAccess.repositories.NotificationRepository;
import com.platform.libraryManager.logic.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public NotificationController(NotificationRepository notificationRepository,
                                  NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    // Get all notifications for the logged-in user
    @GetMapping
    public List<Notification> getUserNotifications(@AuthenticationPrincipal User user) {
        return notificationRepository.findByUserOrderByIdDesc(user);
    }

    // Mark a single notification as read
    @PostMapping("/{id}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notification not found"));

        if (!notification.getUser().equals(user)) {
            return ResponseEntity.status(403).build();
        }

        notification.setRead(true);
        notificationRepository.save(notification);
        return ResponseEntity.ok().build();
    }

    // Mark all notifications as read
    @PostMapping("/read-all")
    public ResponseEntity<?> markAllAsRead(@AuthenticationPrincipal User user) {
        List<Notification> notifications = notificationRepository.findByUserOrderByIdDesc(user);
        notifications.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifications);
        return ResponseEntity.ok().build();
    }
}
