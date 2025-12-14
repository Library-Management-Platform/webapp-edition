package com.platform.libraryManager.responses.endpoints.auth.signUp;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class AuthSignUpResponse extends Response {

    public AuthSignUpResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
