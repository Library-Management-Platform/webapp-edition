package com.platform.libraryManager.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ADMIN")
@Table(name = "admins")
public class Admin extends User {

    public Admin() {}

    public Admin(
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(username, email, password, createdAt, updatedAt);
    }

    public Admin(
            Long id,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(id, username, email, password, createdAt, updatedAt);
    }
}
