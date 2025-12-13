package com.platform.libraryManager.responses.endpoints.auth.signUp;

import com.platform.libraryManager.responses.ResponseType;

public class AuthSignUpSuccessResponse extends AuthSignUpResponse {


    public AuthSignUpSuccessResponse() {
        super(201, "Client Account has been created successfully", ResponseType.SUCCESS);
    }

    @Override public boolean success() { return true; }
}
