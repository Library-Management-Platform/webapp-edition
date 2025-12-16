package com.platform.libraryManager.dataAccess.repositories;

import com.platform.libraryManager.dataAccess.models.Admin;
import com.platform.libraryManager.dto.searchQueryParams.AdminSearchQueryParams;
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

    @Query(value = """
    WITH RECURSIVE admin_tree AS (
        SELECT u.id, u.username, u.email, u.password, u.created_at, u.updated_at,
               u.user_type, u.verified, a.parent_admin_id
        FROM admins a
        JOIN users u ON a.id = u.id
        WHERE a.id = :rootId
    
        UNION ALL
    
        SELECT u.id, u.username, u.email, u.password, u.created_at, u.updated_at,
               u.user_type, u.verified, a.parent_admin_id
        FROM admins a
        JOIN users u ON a.id = u.id
        JOIN admin_tree at ON a.parent_admin_id = at.id
    )
    SELECT *
    FROM admin_tree
    WHERE id != :rootId
    """, nativeQuery = true)
    List<Admin> findAllDescendants(@Param("rootId") Long rootId);



    Optional<Admin> findByUsername(String username);
    List<Admin> findAll();



}
