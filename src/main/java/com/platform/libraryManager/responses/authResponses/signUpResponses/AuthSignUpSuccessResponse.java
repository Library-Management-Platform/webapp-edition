package com.platform.libraryManager.responses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.Response;

public class AuthSignUpSuccessResponse extends Response {


    public AuthSignUpSuccessResponse() {
        super(
                201,
                "Client Account has been created successfully"
        );
    }

}
