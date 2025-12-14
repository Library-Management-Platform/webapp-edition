package com.platform.libraryManager.responses.endpoints.emailVerification.createLink;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class CreateEmailVerificationLinkErrorResponse extends CreateEmailVerificationLinkResponse implements IErrorResponse {

    public CreateEmailVerificationLinkErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
