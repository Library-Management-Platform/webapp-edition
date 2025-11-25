package com.platform.libraryManager.repositories;


import com.platform.libraryManager.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ClientRespository extends JpaRepository<Client, Long> {

    Optional<Client> findByUsername(String username);
}
