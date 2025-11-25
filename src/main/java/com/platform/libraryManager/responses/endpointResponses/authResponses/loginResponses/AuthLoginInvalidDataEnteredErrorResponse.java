package com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class AuthLoginInvalidDataEnteredErrorResponse extends ErrorResponse implements AuthLoginResponse {

    public AuthLoginInvalidDataEnteredErrorResponse() {
        super(
                400,
                "The provided login credentials are incorrect. Please check your username and password and try again."
        );
    }
}
