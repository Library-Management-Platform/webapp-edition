package com.platform.libraryManager.responses.endpoints.emailVerification.createLink;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class CreateEmailVerificationLinkResponse extends Response {

    public CreateEmailVerificationLinkResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
