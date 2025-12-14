package com.platform.libraryManager.responses.endpoints.emailVerification.verify;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class VerifyEmailErrorResponse extends VerifyEmailResponse implements IErrorResponse {

    public VerifyEmailErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
