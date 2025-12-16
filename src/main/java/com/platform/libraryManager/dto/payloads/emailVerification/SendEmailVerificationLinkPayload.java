package com.platform.libraryManager.dto.payloads.emailVerification;

import com.platform.libraryManager.dataAccess.models.User;

public class SendEmailVerificationLinkPayload {

    private User user;

    public SendEmailVerificationLinkPayload(User user) { setUser(user); }

    public User getUser() { return user; }
    private void setUser(User user) { this.user = user; }
}
