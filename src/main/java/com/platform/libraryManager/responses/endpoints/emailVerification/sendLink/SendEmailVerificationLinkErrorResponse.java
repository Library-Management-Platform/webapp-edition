package com.platform.libraryManager.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class SendEmailVerificationLinkErrorResponse extends SendEmailVerificationLinkResponse implements IErrorResponse {

    public SendEmailVerificationLinkErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
