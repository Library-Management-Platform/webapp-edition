package com.platform.libraryManager.logic.managers.emailVerification;


import com.platform.libraryManager.dataAccess.models.EmailVerificationLink;
import com.platform.libraryManager.dataAccess.repositories.EmailVerificationRepository;
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
