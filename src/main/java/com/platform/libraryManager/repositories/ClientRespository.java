package com.platform.libraryManager.repositories;


import com.platform.libraryManager.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRespository extends JpaRepository<Client, Long> { }
