package com.platform.libraryManager.dto.payloads.resource;

import com.platform.libraryManager.dataAccess.enums.ResourceCategoryEnum;
import com.platform.libraryManager.dataAccess.enums.ResourceTypeEnum;

public class AddResourcePayload {

    private String title;
    private String author;
    private ResourceCategoryEnum category;
    private ResourceTypeEnum type;
    private Long libraryId;

    public AddResourcePayload() { }

    public AddResourcePayload(
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

    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
}
