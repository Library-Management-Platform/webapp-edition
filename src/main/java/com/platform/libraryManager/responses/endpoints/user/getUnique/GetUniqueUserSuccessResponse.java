package com.platform.libraryManager.responses.endpoints.user.getUnique;

import com.platform.libraryManager.models.User;
import com.platform.libraryManager.responses.ResponseType;

public class GetUniqueUserSuccessResponse extends GetUniqueUserResponse {

    public GetUniqueUserSuccessResponse(User user) {
        super(200, "success", ResponseType.SUCCESS);
        setUser(user);
    }

    @Override public boolean success() { return true; }

}
