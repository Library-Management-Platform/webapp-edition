package com.platform.libraryManager.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true) private List<Resource> resources;
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true) private List<Librarian> librarians;

    private String name;
    @Column(unique = true) private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Library() {}

    public Library(
            String name,
            String address,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        setName(name);
        setAddress(address);
        setCreationDate(createdAt);
        setUpdateDate(updatedAt);
    }


    public Library(
            Long id,
            String name,
            String address,
            List<Resource> resources
    ) {
        setId(id);
        setName(name);
        setAddress(address);
        setResources(resources);
        setCreationDate(createdAt);
        setUpdateDate(updatedAt);
    }

    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public List<Resource> getResources() { return resources; }
    public void setResources(List<Resource> resources) { this.resources = resources; }

    public List<Librarian> getLibrarians() { return librarians; }
    public void setLibrarians(List<Librarian> librarians) { this.librarians = librarians; }

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdateDate() { return updatedAt; }
    private void setUpdateDate(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
