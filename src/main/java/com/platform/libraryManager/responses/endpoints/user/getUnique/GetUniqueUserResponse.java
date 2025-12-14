package com.platform.libraryManager.responses.endpoints.user.getUnique;

import com.platform.libraryManager.models.User;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class GetUniqueUserResponse extends Response {

    private User user;

    public GetUniqueUserResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
