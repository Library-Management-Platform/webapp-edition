package com.platform.libraryManager.dataAccess.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("ADMIN")
@Table(name = "admins")
public class Admin extends User {

    @ManyToOne @JoinColumn(name = "parent_admin_id") private Admin parent;
    @OneToMany(mappedBy = "parent") private List<Admin> children;

    public Admin() {}

    public Admin(
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Admin parent
    ) {
        super(username, email, password, createdAt, updatedAt);
        setParent(parent);
    }

    public Admin(
            Long id,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Admin parent
    ) {
        super(id, username, email, password, createdAt, updatedAt);
        setParent(parent);
    }


    @PreRemove
    private void reassignChildren() {
        if (children != null) for (Admin child : children) child.setParent(this.parent); // assign to grandparent
    }

    public Admin getParent() { return parent; }
    private void setParent(Admin parent) { this.parent = parent; }
}
