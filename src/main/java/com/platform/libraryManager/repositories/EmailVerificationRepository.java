package com.platform.libraryManager.repositories;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.EmailVerificationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationLink, Long> {

    Optional<EmailVerificationLink> findByToken(String token);

}
