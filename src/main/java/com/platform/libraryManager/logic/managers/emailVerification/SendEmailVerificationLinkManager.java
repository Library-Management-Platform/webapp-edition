package com.platform.libraryManager.logic.managers.emailVerification;


import com.platform.libraryManager.setup.configurationProperties.SMTPConfigurationProperties;
import com.platform.libraryManager.setup.configurationProperties.WebserverConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendEmailVerificationLinkManager {

    @Autowired private WebserverConfigurationProperties webserverConfigurationProperties;
    @Autowired private SMTPConfigurationProperties smtpConfigurationProperties;


    public SimpleMailMessage createEmail(String recipient, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(smtpConfigurationProperties.getUsername());
        message.setTo(recipient);
        message.setSubject("Email Verification");
        message.setText("Account verification link: " + token);

        return message;
    }


    public String getVerificationLink(String token) {
        return webserverConfigurationProperties.getFullAddress() + "/email-verification/" + token;
    }

}
