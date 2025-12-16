package com.platform.libraryManager.dto.responses.endpoints.auth.signUp;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AuthSignUpErrorResponse extends AuthSignUpResponse implements IErrorResponse {

    public AuthSignUpErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }


}
