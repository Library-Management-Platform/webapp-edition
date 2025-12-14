package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AuthLoginErrorResponse extends AuthLoginResponse implements IErrorResponse {

    public AuthLoginErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
