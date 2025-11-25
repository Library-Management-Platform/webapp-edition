package com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class AuthLoginUserNotFoundErrorResponse extends ErrorResponse implements AuthLoginResponse {

    public AuthLoginUserNotFoundErrorResponse() {
        super(
                404,
                "The username you entered does not exist in our records. Please check your username and try again."
        );
    }

}
