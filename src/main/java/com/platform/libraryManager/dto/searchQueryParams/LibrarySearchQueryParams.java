package com.platform.libraryManager.dto.searchQueryParams;


import java.time.LocalDateTime;

public class LibrarySearchQueryParams {

    private Long id;
    private String name;
    private String address;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public LibrarySearchQueryParams(
            Long id,
            String name,
            String address,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        setId(id);
        setName(name);
        setAddress(address);
        setCreationDate(createdAt);
        setUpdateDate(updatedAt);
    }


    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    private void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    private void setAddress(String address) { this.address = address; }

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdateDate() { return updatedAt; }
    private void setUpdateDate(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
