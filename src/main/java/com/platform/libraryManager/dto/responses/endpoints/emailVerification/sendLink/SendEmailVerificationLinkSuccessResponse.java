package com.platform.libraryManager.dto.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class SendEmailVerificationLinkSuccessResponse extends SendEmailVerificationLinkResponse implements ISuccessResponse {

    public SendEmailVerificationLinkSuccessResponse() {
        super(201, "Client account has been created successfully. we have sent you a verification email", ResponseType.SUCCESS);
    }

}
