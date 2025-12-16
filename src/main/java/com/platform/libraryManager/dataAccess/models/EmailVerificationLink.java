package com.platform.libraryManager.dataAccess.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_links")
public class EmailVerificationLink {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name = "user_id") private User user;

    @Column(unique = true) private String token;
    private boolean visited;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public EmailVerificationLink() {}

    public EmailVerificationLink(
            String token,
            User user,
            LocalDateTime createdAt,
            LocalDateTime expiresAt
    ) {
        setToken(token);
        setUser(user);
        setCreationDate(createdAt);
        setExpirationDate(expiresAt);
    }


    public EmailVerificationLink(
            Long id,
            String token,
            User user,
            LocalDateTime createdAt,
            LocalDateTime expiresAt
    ) {
        setId(id);
        setToken(token);
        setUser(user);
        setCreationDate(createdAt);
        setExpirationDate(expiresAt);
    }



    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public String getToken() { return token; }
    private void setToken(String token) { this.token = token; }

    public boolean isVisited() { return visited; }
    public void markAsVisited() { this.visited = true; }

    public User getUser() { return user; }
    private void setUser(User user) { this.user = user; }

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getExpirationDate() { return expiresAt; }
    private void setExpirationDate(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }



}
