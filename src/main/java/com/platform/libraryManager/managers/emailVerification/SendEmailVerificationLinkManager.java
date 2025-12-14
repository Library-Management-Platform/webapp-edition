package com.platform.libraryManager.managers.emailVerification;


import com.platform.libraryManager.configurationProperties.SMTPConfigurationProperties;
import com.platform.libraryManager.configurationProperties.WebserverConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class SendEmailVerificationLinkManager {

    @Autowired private WebserverConfigurationProperties webserverConfigurationProperties;
    @Autowired private SMTPConfigurationProperties smtpConfigurationProperties;


    public SimpleMailMessage createEmail(String recepient, String verificationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(smtpConfigurationProperties.getUsername());
        message.setTo(recepient);
        message.setSubject("Email Verification");
        message.setText("Account verification link: " + verificationLink);

        return message;
    }


    public String getVerificationLink(String token) {
        return webserverConfigurationProperties.getFullAddress() + "/email-verification/" + "/" + token;
    }

}
