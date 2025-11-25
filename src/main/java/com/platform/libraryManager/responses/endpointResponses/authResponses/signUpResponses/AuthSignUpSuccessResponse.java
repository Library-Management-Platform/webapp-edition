package com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.genericResponses.successResponse.SuccessResponse;

public class AuthSignUpSuccessResponse extends SuccessResponse implements AuthSignUpResponse{


    public AuthSignUpSuccessResponse() {
        super(
                201,
                "Client Account has been created successfully"
        );
    }

}
