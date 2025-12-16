package com.platform.libraryManager.dto.searchQueryParams;

import com.platform.libraryManager.shared.enums.ResourceCategoryEnum;
import com.platform.libraryManager.shared.enums.ResourceStatusEnum;
import com.platform.libraryManager.shared.enums.ResourceTypeEnum;

import java.time.LocalDateTime;

public class ResourceSearchQueryParams {


    private Long id;
    private Long libraryId;

    private String title;
    private String author;
    private String link;

    private ResourceCategoryEnum category;
    private ResourceTypeEnum type;
    private ResourceStatusEnum status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ResourceSearchQueryParams(
            Long id,
            Long libraryId,
            String title,
            String author,
            ResourceCategoryEnum category,
            ResourceTypeEnum type,
            ResourceStatusEnum status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        setId(id);
        setLibraryId(libraryId);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setType(type);
        setStatus(status);
        setCreationDate(createdAt);
        setUpdateDate(updatedAt);
    }


    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public Long getLibraryId() { return libraryId; }
    private void setLibraryId(Long libraryId) { this.libraryId = libraryId; }

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
