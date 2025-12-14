package com.platform.libraryManager.repositories;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByUsername(String username);
}
