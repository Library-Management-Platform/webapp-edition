package com.platform.libraryManager.services;


import com.platform.libraryManager.helpers.JSONHelper;
import com.platform.libraryManager.managers.authManagers.AuthLoginManager;
import com.platform.libraryManager.payloads.authPayloads.LoginAuthPayload;
import com.platform.libraryManager.payloads.authPayloads.SignUpAuthPayload;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.payloads.clientPayloads.GetUniqueClientPayload;
import com.platform.libraryManager.providers.JWTProvider;
import com.platform.libraryManager.providers.PasswordHashingProvider;
import com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses.*;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpInvalidDataEnteredErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpSuccessResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpUserExistsErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientExistsErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientSuccessResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses.GetUniqueClientResponse;
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
            return new AuthLoginUserNotFoundErrorResponse();

        }catch(Exception exception) {
            return new AuthLoginInvalidDataEnteredErrorResponse();
        }
    }





    public AuthSignUpResponse signUp(SignUpAuthPayload signUpAuthPayload) {


        CreateClientResponse createClientResponse = clientService.createClient(new CreateClientPayload(
                signUpAuthPayload.getUsername(),
                signUpAuthPayload.getEmail(),
                passwordHashingProvider.hash(signUpAuthPayload.getPassword())
        ));

        return switch (createClientResponse) {
            case CreateClientSuccessResponse createClientSuccessResponse -> new AuthSignUpSuccessResponse();
            case CreateClientExistsErrorResponse createClientExistsErrorResponse -> new AuthSignUpUserExistsErrorResponse();
            default -> new AuthSignUpInvalidDataEnteredErrorResponse();
        };

    }
}
