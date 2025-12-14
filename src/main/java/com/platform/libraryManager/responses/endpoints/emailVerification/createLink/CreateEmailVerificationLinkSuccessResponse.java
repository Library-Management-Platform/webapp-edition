package com.platform.libraryManager.responses.endpoints.emailVerification.createLink;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class CreateEmailVerificationLinkSuccessResponse extends CreateEmailVerificationLinkResponse implements ISuccessResponse {

    private String token;

    public CreateEmailVerificationLinkSuccessResponse(String token) {
        super(201, "email verification link has been created", ResponseType.SUCCESS);
        setToken(token);
    }

    public String getToken() { return token; }
    private void setToken(String token) { this.token = token; }


}
