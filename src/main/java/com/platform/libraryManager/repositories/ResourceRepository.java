package com.platform.libraryManager.repositories;

import com.platform.libraryManager.enums.ResourceCategoryEnum;
import com.platform.libraryManager.enums.ResourceStatusEnum;
import com.platform.libraryManager.enums.ResourceTypeEnum;
import com.platform.libraryManager.models.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    // Search by title or author (case-insensitive)
    @Query("SELECT r FROM Resource r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(r.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Resource> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            @Param("keyword") String titleKeyword, 
            @Param("keyword") String authorKeyword, 
            Pageable pageable);
    
    // Search by title only
    Page<Resource> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    // Search by author only
    Page<Resource> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    
    // Filter by resource type
    Page<Resource> findByType(ResourceTypeEnum type, Pageable pageable);
    
    // Filter by category
    Page<Resource> findByCategory(ResourceCategoryEnum category, Pageable pageable);
    
    // Filter by status
    Page<Resource> findByStatus(ResourceStatusEnum status, Pageable pageable);
    
    // Find available resources
    @Query("SELECT r FROM Resource r WHERE r.status = 'AVAILABLE'")
    Page<Resource> findAvailableResources(Pageable pageable);
    
    // Find by type and status
    Page<Resource> findByTypeAndStatus(ResourceTypeEnum type, ResourceStatusEnum status, Pageable pageable);
    
    // Find by library
    Page<Resource> findByLibraryId(Long libraryId, Pageable pageable);
}