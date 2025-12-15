package com.platform.libraryManager.repositories;

import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.searchQueryParams.AdminSearchQueryParams;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("""
    SELECT a FROM Admin a
    WHERE (COALESCE(:#{#params.id}, a.id) = a.id)
      AND (COALESCE(LOWER(:#{#params.username}), LOWER(a.username)) LIKE LOWER(CONCAT('%', a.username, '%')))
      AND (COALESCE(LOWER(:#{#params.email}), LOWER(a.email)) LIKE LOWER(CONCAT('%', a.email, '%')))
    """)
    List<Admin> search(@Param("params") AdminSearchQueryParams params);


    @Query("""
    SELECT a FROM Admin a
    WHERE (COALESCE(:#{#params.id}, a.id) = a.id)
      AND (COALESCE(LOWER(:#{#params.username}), LOWER(a.username)) LIKE LOWER(CONCAT('%', a.username, '%')))
      AND (COALESCE(LOWER(:#{#params.email}), LOWER(a.email)) LIKE LOWER(CONCAT('%', a.email, '%')))
    """)
    Optional<Admin> searchOne(@Param("params") AdminSearchQueryParams params);

    List<Admin> findAll();
}
