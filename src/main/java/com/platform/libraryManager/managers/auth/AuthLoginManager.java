package com.platform.libraryManager.managers.auth;


import com.platform.libraryManager.payloads.emailVerification.SendEmailVerificationLinkPayload;
import com.platform.libraryManager.responses.endpoints.user.getUnique.GetUniqueUserResponse;
import com.platform.libraryManager.services.EmailVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import java.util.List;

import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginErrorResponse;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginResponse;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginSuccessResponse;

import com.platform.libraryManager.helpers.JSONHelper;
import com.platform.libraryManager.payloads.auth.LoginAuthPayload;
import com.platform.libraryManager.providers.JWTProvider;
import com.platform.libraryManager.providers.PasswordHashingProvider;

@Component
public class AuthLoginManager {

    @Autowired private EmailVerificationService emailVerificationService;

    @Autowired private PasswordHashingProvider passwordHashingProvider;
    @Autowired private JWTProvider jwtProvider;

    private boolean verifyPassword(String inputPassword, String userHashedPassword) {
        return passwordHashingProvider.verify(inputPassword, userHashedPassword);
    }

    private void setCsrfAuthentication(
            GetUniqueUserResponse getUniqueUserResponse,
            HttpServletRequest request
    ) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        getUniqueUserResponse.getUser().getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority(getUniqueUserResponse.getUser().getUserType().name()))
                )
        );

        request.getSession(true).setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }




    public AuthLoginResponse confirmLogin(
            GetUniqueUserResponse getUniqueUserResponse,
            LoginAuthPayload loginAuthPayload,
            HttpServletRequest request
    ) {

        if(!getUniqueUserResponse.getUser().isVerified()) {
            emailVerificationService.sendEmailVerificationLink(
                    new SendEmailVerificationLinkPayload(getUniqueUserResponse.getUser())
            );

            return new AuthLoginErrorResponse(403, "Account email has not been verified. We have sent you a verification link. check your mail inbox");

        } else if(verifyPassword(loginAuthPayload.getPassword(), getUniqueUserResponse.getUser().getPassword())) {
            setCsrfAuthentication(getUniqueUserResponse, request);

            return new AuthLoginSuccessResponse(
                    jwtProvider.generateToken(JSONHelper.createJSONObject(getUniqueUserResponse.getUser().getUsername())),
                    getUniqueUserResponse.getUser()
            );

        }

        return new AuthLoginErrorResponse(401, "The password you entered is incorrect. Please verify your password and try again.");
    }
}
