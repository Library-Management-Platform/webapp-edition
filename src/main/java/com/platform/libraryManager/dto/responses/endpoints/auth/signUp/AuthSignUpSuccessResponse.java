package com.platform.libraryManager.dto.responses.endpoints.auth.signUp;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AuthSignUpSuccessResponse extends AuthSignUpResponse implements ISuccessResponse {


    public AuthSignUpSuccessResponse() {
        super(201, "Client Account has been created successfully", ResponseType.SUCCESS);
    }

}
