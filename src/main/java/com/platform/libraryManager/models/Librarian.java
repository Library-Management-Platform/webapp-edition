package com.platform.libraryManager.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("LIBRARIAN")
@Table(name = "librarians")
public class Librarian extends User {

    @ManyToOne @JoinColumn(name = "library_id") private Library library;

    public Librarian() { super(); }

    public Librarian (
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Library library
    ) {
        super(username, email, password, createdAt, updatedAt);
        setLibrary(library);
    }


    public Librarian(
            Long id,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Library library
    ) {
        super(id, username, email, password, createdAt, updatedAt);
        setLibrary(library);
    }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }

}
