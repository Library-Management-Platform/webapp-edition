package com.platform.libraryManager.managers.emailVerification;


import com.platform.libraryManager.models.EmailVerificationLink;
import com.platform.libraryManager.repositories.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyEmailManager {

    @Autowired private EmailVerificationRepository emailVerificationRepository;

    public void markVerificationLinkAsVisited(EmailVerificationLink emailVerificationLink) {
        emailVerificationLink.markAsVisited();
        emailVerificationRepository.save(emailVerificationLink);
    }
}
