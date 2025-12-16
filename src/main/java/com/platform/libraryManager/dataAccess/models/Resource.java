package com.platform.libraryManager.dataAccess.models;


import com.platform.libraryManager.dataAccess.enums.ResourceCategoryEnum;
import com.platform.libraryManager.dataAccess.enums.ResourceStatusEnum;
import com.platform.libraryManager.dataAccess.enums.ResourceTypeEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resources")
public class Resource {

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true) private List<Loan> loans;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name = "library_id") private Library library;

    private String title;
    private String author;
    private String link;

    @Enumerated(EnumType.STRING) private ResourceCategoryEnum category;
    @Enumerated(EnumType.STRING) private ResourceTypeEnum type;
    @Enumerated(EnumType.STRING) private ResourceStatusEnum status;

    @CreationTimestamp private LocalDateTime createdAt;
    @UpdateTimestamp  private LocalDateTime updatedAt;


    public Resource() {}

    public Resource(
            Library library,
            String title,
            String author,
            ResourceCategoryEnum category,
            ResourceTypeEnum type
    ) {
        setLibrary(library);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setType(type);
        setStatus(ResourceStatusEnum.AVAILABLE);
    }

    public Resource(
            Long id,
            Library library,
            String title,
            String author,
            ResourceCategoryEnum category,
            ResourceTypeEnum type,
            ResourceStatusEnum status
    ) {
        setId(id);
        setLibrary(library);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setType(type);
        setStatus(status);
    }

    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public Library getLibrary() { return library; }
    private void setLibrary(Library library) { this.library = library; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public ResourceCategoryEnum getCategory() { return category; }
    public void setCategory(ResourceCategoryEnum category) { this.category = category; }

    public ResourceTypeEnum getType() { return type; }
    public void setType(ResourceTypeEnum type) { this.type = type; }

    public ResourceStatusEnum getStatus() { return status; }
    public void setStatus(ResourceStatusEnum status) { this.status = status; }

    public LocalDateTime getCreationDate() { return createdAt; }
    private void setCreationDate(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdateDate() { return updatedAt; }
    private void setUpdateDate(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

}
