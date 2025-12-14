package com.platform.libraryManager.services;


import com.platform.libraryManager.repositories.EmailVerificationRepository;

import com.platform.libraryManager.helpers.StringHelper;

import com.platform.libraryManager.models.EmailVerificationLink;

import com.platform.libraryManager.factories.EmailVerificationLinkFactory;

import com.platform.libraryManager.managers.emailVerification.SendEmailVerificationLinkManager;

import com.platform.libraryManager.payloads.emailVerification.CreateEmailVerificationLinkPayload;
import com.platform.libraryManager.payloads.emailVerification.SendEmailVerificationLinkPayload;

import com.platform.libraryManager.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkErrorResponse;
import com.platform.libraryManager.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkResponse;
import com.platform.libraryManager.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkSuccessResponse;
import com.platform.libraryManager.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkErrorResponse;
import com.platform.libraryManager.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkResponse;
import com.platform.libraryManager.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkSuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService {

    @Autowired private SendEmailVerificationLinkManager sendEmailVerificationLinkManager;
    @Autowired private EmailVerificationRepository emailVerificationRepository;

    @Autowired private JavaMailSender mailSender;



    public void verifyEmail() {}




    public SendEmailVerificationLinkResponse sendEmailVerificationLink(SendEmailVerificationLinkPayload sendEmailVerificationLinkPayload) {

        try {

            final CreateEmailVerificationLinkResponse createEmailVerificationLinkResponse = createEmailVerificationLink(sendEmailVerificationLinkPayload);


            if(createEmailVerificationLinkResponse.getCode() == 201) {
                final SimpleMailMessage email = sendEmailVerificationLinkManager.createEmail(
                        sendEmailVerificationLinkPayload.getUser().getEmail(),
                        sendEmailVerificationLinkManager.getVerificationLink(((CreateEmailVerificationLinkSuccessResponse) createEmailVerificationLinkResponse).getToken())
                );

                mailSender.send(email);
                return new SendEmailVerificationLinkSuccessResponse();
            }

            return new SendEmailVerificationLinkErrorResponse(400, "Failed to send verification link to your account's email");


        }catch(Exception exception) {
           return new SendEmailVerificationLinkErrorResponse(400, "Failed to send verification link to your account's email");
        }

    }


    private CreateEmailVerificationLinkResponse createEmailVerificationLink(SendEmailVerificationLinkPayload sendEmailVerificationLinkPayload) {

        try {
            final EmailVerificationLink emailVerificationLink = EmailVerificationLinkFactory.create(
                    new CreateEmailVerificationLinkPayload(
                            sendEmailVerificationLinkPayload.getUser(),
                            StringHelper.generateRandomString(10)
                    )
            );

            emailVerificationRepository.save(emailVerificationLink);
            return new CreateEmailVerificationLinkSuccessResponse(emailVerificationLink.getToken());

        }catch(Exception exception) {
            return new CreateEmailVerificationLinkErrorResponse(400, "email verification link creation error");
        }
    }



}
