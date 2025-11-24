package com.platform.libraryManager.responses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.ErrorResponse;

public class AuthSignUpInvalidDataEnteredErrorResponse extends ErrorResponse {

    public AuthSignUpInvalidDataEnteredErrorResponse() {
        super(
                400,
                "The provided sign up credentials are incorrect. Please check the data you entered and try again."
        );
    }
}
