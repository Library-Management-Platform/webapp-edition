package com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class AuthLoginWrongPasswordErrorResponse extends ErrorResponse implements AuthLoginResponse {

    public AuthLoginWrongPasswordErrorResponse() {
        super(
                401,
                "The password you entered is incorrect. Please verify your password and try again."
        );
    }
}
