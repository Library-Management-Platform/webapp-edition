package com.platform.libraryManager.dto.responses.endpoints.auth.signUp;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class AuthSignUpResponse extends Response {

    public AuthSignUpResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
