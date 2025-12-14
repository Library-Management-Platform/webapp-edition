package com.platform.libraryManager.payloads.emailVerification;

import com.platform.libraryManager.models.User;

public class CreateEmailVerificationLinkPayload {

    private User user;
    private String token;

    public CreateEmailVerificationLinkPayload(
            User user,
            String token
    ) {
        setUser(user);
        setToken(token);
    }

    public User getUser() { return user; }
    private void setUser(User user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
