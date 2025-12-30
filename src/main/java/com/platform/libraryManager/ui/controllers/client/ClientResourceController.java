package com.platform.libraryManager.ui.controllers.client;

import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dataAccess.repositories.ResourceRepository;
import com.platform.libraryManager.shared.enums.ResourceCategoryEnum;
import com.platform.libraryManager.shared.enums.ResourceStatusEnum;
import com.platform.libraryManager.shared.enums.ResourceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client/resources")
public class ClientResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * Main page to browse all resources
     */
    @GetMapping()
    public String browseResources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Resource> resourcePage;
        
        // Apply filters if provided
        if (keyword != null && !keyword.isEmpty()) {
            // Search by title or author
            resourcePage = resourceRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword, pageable);
            model.addAttribute("keyword", keyword);
        } else if (type != null && !type.isEmpty()) {
            // Filter by resource type
            try {
                ResourceTypeEnum resourceType =
                    ResourceTypeEnum.valueOf(type);
                resourcePage = resourceRepository.findByType(resourceType, pageable);
                model.addAttribute("selectedType", type);
            } catch (IllegalArgumentException e) {
                // If invalid type, show all resources
                resourcePage = resourceRepository.findAll(pageable);
            }
        } else if (category != null && !category.isEmpty()) {
            // Filter by category
            try {
                ResourceCategoryEnum resourceCategory =
                    ResourceCategoryEnum.valueOf(category);
                resourcePage = resourceRepository.findByCategory(resourceCategory, pageable);
                model.addAttribute("selectedCategory", category);
            } catch (IllegalArgumentException e) {
                // If invalid category, show all resources
                resourcePage = resourceRepository.findAll(pageable);
            }
        } else {
            // Get all resources
            resourcePage = resourceRepository.findAll(pageable);
        }
        
        // Add attributes to model
        model.addAttribute("resources", resourcePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resourcePage.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("totalItems", resourcePage.getTotalElements());
        
        // Add enums for filter dropdowns
        model.addAttribute("allTypes", ResourceTypeEnum.values());
        model.addAttribute("allCategories", ResourceCategoryEnum.values());
        model.addAttribute("allStatuses", ResourceStatusEnum.values());
        
        return "client/browse-resources";
    }


  
    /**
     * Search resources with advanced filters
     */
    @GetMapping("/search")
    public String searchResources(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            Model model) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        Page<Resource> resourcePage;
        
        // Determine search type
        if (query != null && !query.isEmpty()) {
            // Simple search by keyword
            resourcePage = resourceRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query, pageable);
            model.addAttribute("keyword", query);
        } else {
            // Advanced search with multiple criteria
            // For simplicity, we'll use a simple approach. You can implement more complex queries as needed
            resourcePage = resourceRepository.findAll(pageable);
            
            // Add filter attributes
            if (title != null && !title.isEmpty()) {
                resourcePage = resourceRepository.findByTitleContainingIgnoreCase(title, pageable);
                model.addAttribute("title", title);
            }
            if (author != null && !author.isEmpty()) {
                resourcePage = resourceRepository.findByAuthorContainingIgnoreCase(author, pageable);
                model.addAttribute("author", author);
            }
            if (type != null && !type.isEmpty()) {
                try {
                    ResourceTypeEnum resourceType =
                        ResourceTypeEnum.valueOf(type);
                    resourcePage = resourceRepository.findByType(resourceType, pageable);
                    model.addAttribute("type", type);
                } catch (IllegalArgumentException e) {
                    // Invalid type, ignore filter
                }
            }
        }
        
        model.addAttribute("resources", resourcePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resourcePage.getTotalPages());
        model.addAttribute("pageSize", size);
        model.addAttribute("totalItems", resourcePage.getTotalElements());
        
        return "client/browse-resources";
    }

    /**
     * Quick filter by resource type
     */
    @GetMapping("/type/{type}")
    public String filterByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            Model model) {
        
        try {
            ResourceTypeEnum resourceType =
                ResourceTypeEnum.valueOf(type.toUpperCase());
            
            Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
            Page<Resource> resourcePage = resourceRepository.findByType(resourceType, pageable);
            
            model.addAttribute("resources", resourcePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resourcePage.getTotalPages());
            model.addAttribute("pageSize", size);
            model.addAttribute("totalItems", resourcePage.getTotalElements());
            model.addAttribute("selectedType", type);
            
            return "client/browse-resources";
        } catch (IllegalArgumentException e) {
            // Invalid type, redirect to all resources
            return "redirect:/client/resources";
        }
    }
}