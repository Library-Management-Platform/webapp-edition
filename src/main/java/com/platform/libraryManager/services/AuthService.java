package com.platform.libraryManager.services;


import com.platform.libraryManager.payloads.authPayloads.SignUpAuthPayload;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpInvalidDataEnteredErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpSuccessResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses.AuthSignUpUserExistsErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientExistsErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired ClientService clientService;
    @Autowired PasswordEncoder passwordEncoder;

    public void login(SignUpAuthPayload signUpAuthPayload) {

        try {


        }catch(Exception exception) {

        }
    }


    public AuthSignUpResponse signUp(SignUpAuthPayload signUpAuthPayload) {


        CreateClientResponse createClientResponse = clientService.createClient(new CreateClientPayload(
                signUpAuthPayload.getUsername(),
                signUpAuthPayload.getEmail(),
                passwordEncoder.encode(signUpAuthPayload.getPassword())
        ));

        return switch (createClientResponse) {
            case CreateClientSuccessResponse createClientSuccessResponse -> new AuthSignUpSuccessResponse();
            case CreateClientExistsErrorResponse createClientExistsErrorResponse -> new AuthSignUpUserExistsErrorResponse();
            default -> new AuthSignUpInvalidDataEnteredErrorResponse();
        };

    }
}
