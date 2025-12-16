package com.platform.libraryManager.dto.responses.endpoints.emailVerification.createLink;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class CreateEmailVerificationLinkResponse extends Response {

    public CreateEmailVerificationLinkResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
