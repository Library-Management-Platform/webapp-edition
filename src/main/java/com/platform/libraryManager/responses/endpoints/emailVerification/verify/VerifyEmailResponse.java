package com.platform.libraryManager.responses.endpoints.emailVerification.verify;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class VerifyEmailResponse extends Response {

    public VerifyEmailResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
