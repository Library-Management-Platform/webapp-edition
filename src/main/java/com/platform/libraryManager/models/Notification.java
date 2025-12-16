package com.platform.libraryManager.models;

import com.platform.libraryManager.enums.LoanStatusEnum;
import com.platform.libraryManager.enums.NotificationTypeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    private LocalDateTime sentAt;

    @Column(name = "is_read")
    private boolean read;
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private NotificationTypeEnum type;

    public Notification() {
    }

    public Notification(
            String message,
            LocalDateTime date,
            NotificationTypeEnum type,
            User user) {
        setMessage(message);
        setDate(date);
        setType(type);
        setRead(false);
        setUser(user);
    }

    public Notification(
            Long id,
            String message,
            LocalDateTime date,
            NotificationTypeEnum type,
            boolean read,
            User user) {
        setId(id);
        setMessage(message);
        setDate(date);
        setType(type);
        setRead(read);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getDate() {
        return sentAt;
    }

    private void setDate(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    private void setType(NotificationTypeEnum type) {
        this.type = type;
    }

}
