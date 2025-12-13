package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.ResponseType;

public abstract class AuthLoginResponse extends Response {

    public AuthLoginResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
