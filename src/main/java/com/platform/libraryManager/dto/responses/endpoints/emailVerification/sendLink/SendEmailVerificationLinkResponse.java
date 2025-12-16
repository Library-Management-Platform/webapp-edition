package com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class SendEmailVerificationLinkResponse extends Response {

    public SendEmailVerificationLinkResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
