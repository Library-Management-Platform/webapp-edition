package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.responses.ResponseType;

public class AuthLoginSuccessResponse extends AuthLoginResponse {

    private String token;

    public AuthLoginSuccessResponse(String token) {
        super(200, "login success", ResponseType.SUCCESS);
        setToken(token);
    }

    @Override public boolean success() { return true; }


    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
