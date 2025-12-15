package com.platform.libraryManager.repositories;


import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findByAddress(String address);
    List<Library> findAll();

    @Query("""
        SELECT l FROM Library l
        WHERE (:#{#params.id} IS NULL OR l.id = :#{#params.id})
          AND (:#{#params.name} IS NULL OR LOWER(l.name) LIKE LOWER(CONCAT('%', :#{#params.name}, '%')))
          AND (:#{#params.address} IS NULL OR LOWER(l.address) LIKE LOWER(CONCAT('%', :#{#params.address}, '%')))
    """)
    List<Library> search(@Param("params") LibrarySearchQueryParams params);


    @Query("""
        SELECT l FROM Library l
        WHERE (COALESCE(:#{#params.id}, l.id) = l.id)
          AND (COALESCE(LOWER(:#{#params.name}), LOWER(l.name)) LIKE LOWER(CONCAT('%', l.name, '%')))
          AND (COALESCE(LOWER(:#{#params.address}), LOWER(l.address)) LIKE LOWER(CONCAT('%', l.address, '%')))
    """)
    Optional<Library> searchOne(@Param("params") LibrarySearchQueryParams params);


}
