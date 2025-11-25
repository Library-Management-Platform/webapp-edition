package com.platform.libraryManager.payloads.clientPayloads;


import java.time.LocalDateTime;

public class GetUniqueClientPayload {

    private Long id;

    private String username;
    private String email;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public GetUniqueClientPayload(
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


    public GetUniqueClientPayload(
            String username
    ) {
        setUsername(username);
    }


    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdateDate() { return updatedAt; }
    private void setUpdateDate(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
