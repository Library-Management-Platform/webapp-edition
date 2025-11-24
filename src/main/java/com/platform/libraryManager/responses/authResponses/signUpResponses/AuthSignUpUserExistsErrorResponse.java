package com.platform.libraryManager.responses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.ErrorResponse;

public class AuthSignUpUserExistsErrorResponse extends ErrorResponse {

    public AuthSignUpUserExistsErrorResponse() {
        super(
                409,
                "An account with the provided username/email already exists. Please enter a different username/email to create a new account"
        );
    }
}
