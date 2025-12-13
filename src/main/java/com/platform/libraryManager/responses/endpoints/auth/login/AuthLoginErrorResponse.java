package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.responses.ResponseType;

public class AuthLoginErrorResponse extends AuthLoginResponse {

    public AuthLoginErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }


    @Override public boolean success() { return false; }
}
