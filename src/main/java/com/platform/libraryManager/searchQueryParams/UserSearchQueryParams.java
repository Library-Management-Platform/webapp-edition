package com.platform.libraryManager.searchQueryParams;


import java.time.LocalDateTime;

public class UserSearchQueryParams {

    private Long id;

    private String username;
    private String email;
    private boolean verified;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public UserSearchQueryParams(
            Long id,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        setId(id);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setCreationDate(createdAt);
        setUpdateDate(updatedAt);
    }

    public String getUsername() { return username; }
    private void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    private void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    private void setPassword(String password) { this.password = password; }

    public boolean isVerified() { return verified; }
    private void verify() { this.verified = true;}

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdateDate() { return updatedAt; }
    private void setUpdateDate(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
