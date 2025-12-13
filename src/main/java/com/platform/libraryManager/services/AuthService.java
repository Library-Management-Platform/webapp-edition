package com.platform.libraryManager.services;


import com.platform.libraryManager.providers.PasswordHashingProvider;

import com.platform.libraryManager.managers.authManagers.AuthLoginManager;
import com.platform.libraryManager.payloads.authPayloads.LoginAuthPayload;
import com.platform.libraryManager.payloads.authPayloads.SignUpAuthPayload;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.payloads.clientPayloads.GetUniqueClientPayload;




import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginErrorResponse;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginResponse;


import com.platform.libraryManager.responses.endpoints.auth.signUp.AuthSignUpErrorResponse;
import com.platform.libraryManager.responses.endpoints.auth.signUp.AuthSignUpResponse;
import com.platform.libraryManager.responses.endpoints.auth.signUp.AuthSignUpSuccessResponse;


import com.platform.libraryManager.responses.endpoints.client.create.CreateClientErrorResponse;
import com.platform.libraryManager.responses.endpoints.client.create.CreateClientResponse;
import com.platform.libraryManager.responses.endpoints.client.create.CreateClientSuccessResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired ClientService clientService;
    @Autowired AuthLoginManager authLoginManager;
    @Autowired PasswordHashingProvider passwordHashingProvider;


    public AuthLoginResponse login(LoginAuthPayload loginAuthPayload, HttpServletRequest request) {

        final GetUniqueClientResponse getUniqueClientResponse = clientService.getUniqueClient(
                new GetUniqueClientPayload(loginAuthPayload.getUsername())
        );

        try {

            if(getUniqueClientResponse.success()) return authLoginManager.confirmLogin(getUniqueClientResponse, loginAuthPayload, request);
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
}
