package com.platform.libraryManager.responses.endpoints.emailVerification.verify;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class VerifyEmailSuccessResponse extends VerifyEmailResponse implements ISuccessResponse {

    public VerifyEmailSuccessResponse() {
        super(200, "Your Account's email has been verified", ResponseType.SUCCESS);
    }
}
