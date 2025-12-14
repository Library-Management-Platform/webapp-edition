package com.platform.libraryManager.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class SendEmailVerificationLinkResponse extends Response {

    public SendEmailVerificationLinkResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
