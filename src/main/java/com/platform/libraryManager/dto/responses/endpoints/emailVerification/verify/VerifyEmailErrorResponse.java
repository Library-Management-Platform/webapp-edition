package com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class VerifyEmailErrorResponse extends VerifyEmailResponse implements IErrorResponse {

    public VerifyEmailErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
