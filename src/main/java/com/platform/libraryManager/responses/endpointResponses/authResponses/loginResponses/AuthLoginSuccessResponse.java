package com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses;

import com.platform.libraryManager.responses.genericResponses.successResponse.SuccessResponse;

public class AuthLoginSuccessResponse extends SuccessResponse implements AuthLoginResponse {

    private String token;

    public AuthLoginSuccessResponse(String token) {
        super(200, "login success");
        setToken(token);
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
