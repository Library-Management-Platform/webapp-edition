package com.platform.libraryManager.models;

import com.platform.libraryManager.enums.RoleEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("LIBRARIAN")
@Table(name = "librarians")
public class Librarian extends User {

    @ManyToOne @JoinColumn(name = "library_id") private Library library;

    public Librarian() { super(); }

    public Librarian (
            RoleEnum role,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Library library
    ) {
        super(role, username, email, password, createdAt, updatedAt);
        setLibrary(library);
    }


    public Librarian(
            Long id,
            RoleEnum role,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Library library
    ) {
        super(id, role, username, email, password, createdAt, updatedAt);
        setLibrary(library);
    }

    public Library getLibrary() { return library; }
    private void setLibrary(Library library) { this.library = library; }

}
