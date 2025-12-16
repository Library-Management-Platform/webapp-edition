package com.platform.libraryManager.dto.responses.endpoints.user.getUnique;

import com.platform.libraryManager.dataAccess.models.User;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class GetUniqueUserResponse extends Response {

    private User user;

    public GetUniqueUserResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

}
