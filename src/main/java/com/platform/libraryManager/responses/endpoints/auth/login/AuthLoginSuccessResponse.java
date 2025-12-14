package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AuthLoginSuccessResponse extends AuthLoginResponse implements ISuccessResponse {

    private String token;

    public AuthLoginSuccessResponse(String token) {
        super(200, "login success", ResponseType.SUCCESS);
        setToken(token);
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
