package com.platform.libraryManager.configurations;


import com.platform.libraryManager.configurationProperties.SMTPConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class SMTPConfiguration {

    @Autowired private SMTPConfigurationProperties smtpConfigurationProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(smtpConfigurationProperties.getHost());
        mailSender.setPort(smtpConfigurationProperties.getPort());
        mailSender.setUsername(smtpConfigurationProperties.getUsername());
        mailSender.setPassword(smtpConfigurationProperties.getPassword());
        mailSender.setProtocol("smtp");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", String.valueOf( smtpConfigurationProperties.isAuth()));
        props.put("mail.smtp.starttls.enable", String.valueOf(smtpConfigurationProperties.isStarttlsEnable()));
        props.put("mail.smtp.starttls.required", String.valueOf(smtpConfigurationProperties.isStarttlsRequired()));

        return mailSender;
    }
}
