package com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class AuthSignUpUserExistsErrorResponse extends ErrorResponse implements AuthSignUpResponse {

    public AuthSignUpUserExistsErrorResponse() {
        super(
                409,
                "An account with the provided username/email already exists. Please enter a different username/email to create a new account"
        );
    }
}
