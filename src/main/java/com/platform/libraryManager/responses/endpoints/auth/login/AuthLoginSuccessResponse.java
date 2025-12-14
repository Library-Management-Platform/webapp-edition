package com.platform.libraryManager.responses.endpoints.auth.login;

import com.platform.libraryManager.models.User;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AuthLoginSuccessResponse extends AuthLoginResponse implements ISuccessResponse {

    private User user;
    private String token;

    public AuthLoginSuccessResponse(String token, User user) {
        super(200, "login success", ResponseType.SUCCESS);
        setToken(token);
        setUser(user);
    }

    public String getToken() { return token; }
    private void setToken(String token) { this.token = token; }

    public User getUser() { return user; }
    private void setUser(User user) { this.user = user; }
}
