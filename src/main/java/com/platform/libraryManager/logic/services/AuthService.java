package com.platform.libraryManager.logic.services;


import com.platform.libraryManager.dto.payloads.user.GetUniqueUserPayload;
import com.platform.libraryManager.shared.providers.PasswordHashingProvider;

import com.platform.libraryManager.logic.managers.auth.AuthLoginManager;
import com.platform.libraryManager.dto.payloads.auth.LoginAuthPayload;
import com.platform.libraryManager.dto.payloads.auth.SignUpAuthPayload;
import com.platform.libraryManager.dto.payloads.client.CreateClientPayload;


import com.platform.libraryManager.dto.responses.endpoints.auth.login.AuthLoginErrorResponse;
import com.platform.libraryManager.dto.responses.endpoints.auth.login.AuthLoginResponse;


import com.platform.libraryManager.dto.responses.endpoints.auth.signUp.AuthSignUpErrorResponse;
import com.platform.libraryManager.dto.responses.endpoints.auth.signUp.AuthSignUpResponse;
import com.platform.libraryManager.dto.responses.endpoints.auth.signUp.AuthSignUpSuccessResponse;


import com.platform.libraryManager.dto.responses.endpoints.client.create.CreateClientResponse;
import com.platform.libraryManager.dto.responses.endpoints.user.getUnique.GetUniqueUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private ClientService clientService;
    @Autowired private UserService userService;

    @Autowired private AuthLoginManager authLoginManager;
    @Autowired private PasswordHashingProvider passwordHashingProvider;


    public void logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();

        final HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }


    public AuthLoginResponse login(LoginAuthPayload loginAuthPayload, HttpServletRequest request) {

        final GetUniqueUserResponse getUniqueUserResponse = userService.getUniqueUser(
                new GetUniqueUserPayload(loginAuthPayload.getUsername())
        );

        try {

            if(getUniqueUserResponse.success()) return authLoginManager.confirmLogin(getUniqueUserResponse, loginAuthPayload, request);
            return new AuthLoginErrorResponse(404, "The username you entered does not exist in our records. Please check your username and try again.");

        }catch(Exception exception) {
            return new AuthLoginErrorResponse(400, "The provided login credentials are incorrect. Please check your username and password and try again.");

        }
    }





    public AuthSignUpResponse signUp(SignUpAuthPayload signUpAuthPayload) {


        CreateClientResponse createClientResponse = clientService.createClient(
                new CreateClientPayload(
                    signUpAuthPayload.getUsername(),
                    signUpAuthPayload.getEmail(),
                    passwordHashingProvider.hash(signUpAuthPayload.getPassword())
                )
        );


        return switch (createClientResponse.getCode()) {

            case 201 -> new AuthSignUpSuccessResponse();
            case 409 -> new AuthSignUpErrorResponse(409, "An account with the provided username/email already exists. Please enter a different username/email to create a new account");
            default -> new AuthSignUpErrorResponse(400, "The provided sign up credentials are incorrect. Please check the data you entered and try again.");
        };

    }



    public String getAuthenticationName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
