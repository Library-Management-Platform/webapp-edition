package com.platform.libraryManager.dataAccess.repositories;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dto.searchQueryParams.LibrarianSearchQueryParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

  @Query("""
      SELECT a FROM Librarian a
      WHERE (COALESCE(:#{#params.id}, a.id) = a.id)
        AND (COALESCE(LOWER(:#{#params.username}), LOWER(a.username)) LIKE LOWER(CONCAT('%', a.username, '%')))
        AND (COALESCE(LOWER(:#{#params.email}), LOWER(a.email)) LIKE LOWER(CONCAT('%', a.email, '%')))
      """)
  List<Librarian> search(@Param("params") LibrarianSearchQueryParams params);

  @Query("""
      SELECT a FROM Librarian a
      WHERE (COALESCE(:#{#params.id}, a.id) = a.id)
        AND (COALESCE(LOWER(:#{#params.username}), LOWER(a.username)) LIKE LOWER(CONCAT('%', a.username, '%')))
        AND (COALESCE(LOWER(:#{#params.email}), LOWER(a.email)) LIKE LOWER(CONCAT('%', a.email, '%')))
      """)
  Optional<Librarian> searchOne(@Param("params") LibrarianSearchQueryParams params);

  List<Librarian> findAll();
  Optional<Librarian> findByUsername(String username);

}
