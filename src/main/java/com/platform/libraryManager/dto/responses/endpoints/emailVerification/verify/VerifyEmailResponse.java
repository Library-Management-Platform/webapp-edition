package com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class VerifyEmailResponse extends Response {

    public VerifyEmailResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
