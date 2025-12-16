package com.platform.libraryManager.dto.responses.endpoints.auth.login;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AuthLoginErrorResponse extends AuthLoginResponse implements IErrorResponse {

    public AuthLoginErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
