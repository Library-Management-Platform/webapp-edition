package com.platform.libraryManager.dataAccess.repositories;

import com.platform.libraryManager.dataAccess.enums.ResourceCategoryEnum;
import com.platform.libraryManager.dataAccess.enums.ResourceStatusEnum;
import com.platform.libraryManager.dataAccess.enums.ResourceTypeEnum;
import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.searchQueryParams.ResourceSearchQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Query("""
                SELECT r FROM Resource r
                WHERE (:#{#params.id} IS NULL OR r.id = :#{#params.id})
                  AND (:#{#params.libraryId} IS NULL OR r.library.id = :#{#params.libraryId})
                  AND (:#{#params.title} IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :#{#params.title}, '%')))
                  AND (:#{#params.author} IS NULL OR LOWER(r.author) LIKE LOWER(CONCAT('%', :#{#params.author}, '%')))
                  AND (:#{#params.link} IS NULL OR LOWER(r.link) LIKE LOWER(CONCAT('%', :#{#params.link}, '%')))
                  AND (:#{#params.category} IS NULL OR r.category = :#{#params.category})
                  AND (:#{#params.type} IS NULL OR r.type = :#{#params.type})
                  AND (:#{#params.status} IS NULL OR r.status = :#{#params.status})
                  AND (:#{#params.creationDate} IS NULL OR r.createdAt = :#{#params.creationDate})
                  AND (:#{#params.updateDate} IS NULL OR r.updatedAt = :#{#params.updateDate})
            """)
    Optional<Resource> searchOne(@Param("params") ResourceSearchQueryParams params);
}