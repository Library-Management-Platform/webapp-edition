package com.platform.libraryManager.payloads.resource;

import com.platform.libraryManager.enums.ResourceCategoryEnum;
import com.platform.libraryManager.enums.ResourceStatusEnum;
import com.platform.libraryManager.enums.ResourceTypeEnum;

public class EditResourcePayload {

    private String title;
    private String author;
    private ResourceCategoryEnum category;
    private ResourceTypeEnum type;
    private ResourceStatusEnum status;
    private Long libraryId;

    public EditResourcePayload() { }

    public EditResourcePayload(
            String title,
            String author,
            ResourceCategoryEnum category,
            ResourceTypeEnum type,
            Long libraryId
    ){
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setType(type);
        setLibraryId(libraryId);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public ResourceCategoryEnum getCategory() { return category; }
    public void setCategory(ResourceCategoryEnum category) { this.category = category; }

    public ResourceTypeEnum getType() { return type; }
    public void setType(ResourceTypeEnum type) { this.type = type; }

    public ResourceStatusEnum getStatus() { return status; }
    public void setStatus(ResourceStatusEnum status) { this.status = status; }

    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
}
