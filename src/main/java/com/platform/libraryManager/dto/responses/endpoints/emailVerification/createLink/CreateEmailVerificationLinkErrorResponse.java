package com.platform.libraryManager.dto.responses.endpoints.emailVerification.createLink;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class CreateEmailVerificationLinkErrorResponse extends CreateEmailVerificationLinkResponse implements IErrorResponse {

    public CreateEmailVerificationLinkErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
