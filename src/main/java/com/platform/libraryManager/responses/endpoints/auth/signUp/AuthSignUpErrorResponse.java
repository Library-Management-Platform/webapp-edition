package com.platform.libraryManager.responses.endpoints.auth.signUp;

import com.platform.libraryManager.responses.ResponseType;

public class AuthSignUpErrorResponse extends AuthSignUpResponse {

    public AuthSignUpErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }


    @Override public boolean success() { return false; }
}
