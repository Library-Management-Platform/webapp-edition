package com.platform.libraryManager.responses.endpoints.emailVerification.sendLink;

import com.platform.libraryManager.configurationProperties.WebserverConfigurationProperties;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;

public class SendEmailVerificationLinkSuccessResponse extends SendEmailVerificationLinkResponse implements ISuccessResponse {

    public SendEmailVerificationLinkSuccessResponse() {
        super(201, "Client account has been created successfully. we have sent you a verification email", ResponseType.SUCCESS);
    }

}
