package com.platform.libraryManager.responses.endpointResponses.authResponses.signUpResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class AuthSignUpInvalidDataEnteredErrorResponse extends ErrorResponse implements AuthSignUpResponse {

    public AuthSignUpInvalidDataEnteredErrorResponse() {
        super(
                400,
                "The provided sign up credentials are incorrect. Please check the data you entered and try again."
        );
    }
}
