package com.platform.libraryManager.dataAccess.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@Table(name = "clients")
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true) private List<Loan> loans;

    public Client() {}

    public Client(
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(username, email, password, createdAt, updatedAt);
    }

    public Client(
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


