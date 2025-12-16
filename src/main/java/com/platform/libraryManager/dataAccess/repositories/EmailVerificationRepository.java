package com.platform.libraryManager.dataAccess.repositories;

import com.platform.libraryManager.dataAccess.models.EmailVerificationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationLink, Long> {

    Optional<EmailVerificationLink> findByToken(String token);

}
