package com.platform.libraryManager.services;


import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.authPayloads.SignUpAuthPayload;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.authResponses.signUpResponses.AuthSignUpInvalidDataEnteredErrorResponse;
import com.platform.libraryManager.responses.authResponses.signUpResponses.AuthSignUpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired ClientService clientService;

    public void login(SignUpAuthPayload signUpAuthPayload) {

        try {


        }catch(Exception exception) {

        }
    }


    public Response signUp(SignUpAuthPayload signUpAuthPayload) {


        Client client = clientService.createClient(new CreateClientPayload(
                signUpAuthPayload.getUsername(),
                signUpAuthPayload.getEmail(),
                signUpAuthPayload.getPassword()
        ));

        if(!client.isNull()) return new AuthSignUpSuccessResponse();
        return new AuthSignUpInvalidDataEnteredErrorResponse();

    }
}
