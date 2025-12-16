package com.platform.libraryManager.logic.services;


import com.platform.libraryManager.logic.managers.emailVerification.VerifyEmailManager;
import com.platform.libraryManager.dataAccess.repositories.EmailVerificationRepository;

import com.platform.libraryManager.shared.helpers.StringHelper;

import com.platform.libraryManager.dataAccess.models.EmailVerificationLink;

import com.platform.libraryManager.shared.factories.EmailVerificationLinkFactory;

import com.platform.libraryManager.logic.managers.emailVerification.SendEmailVerificationLinkManager;

import com.platform.libraryManager.dto.payloads.emailVerification.CreateEmailVerificationLinkPayload;
import com.platform.libraryManager.dto.payloads.emailVerification.SendEmailVerificationLinkPayload;

import com.platform.libraryManager.dto.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkErrorResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.createLink.CreateEmailVerificationLinkSuccessResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkErrorResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink.SendEmailVerificationLinkSuccessResponse;

import com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify.VerifyEmailErrorResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify.VerifyEmailResponse;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify.VerifyEmailSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EmailVerificationService {

    @Autowired private UserService userService;

    @Autowired private SendEmailVerificationLinkManager sendEmailVerificationLinkManager;
    @Autowired private VerifyEmailManager verifyEmailManager;
    @Autowired private EmailVerificationRepository emailVerificationRepository;

    @Autowired private JavaMailSender mailSender;



    public VerifyEmailResponse verifyEmail(String token) {

        try {

            final EmailVerificationLink emailVerificationLink = emailVerificationRepository.findByToken(token).get();
            userService.verifyUser(emailVerificationLink.getUser());

            if(emailVerificationLink.isVisited()) return new VerifyEmailErrorResponse(410, "Verification Link Expired");

            verifyEmailManager.markVerificationLinkAsVisited(emailVerificationLink);
            return new VerifyEmailSuccessResponse();

        }catch(NoSuchElementException noSuchElementException) {
            return new VerifyEmailErrorResponse(404, "Invalid verification link");

        }catch (Exception exception) {
            return new VerifyEmailErrorResponse(400, "Unknown error. please login again to receive a new verification link");
        }
    }




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
