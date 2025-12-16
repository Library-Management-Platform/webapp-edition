package com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class SendEmailVerificationLinkErrorResponse extends SendEmailVerificationLinkResponse implements IErrorResponse {

    public SendEmailVerificationLinkErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
