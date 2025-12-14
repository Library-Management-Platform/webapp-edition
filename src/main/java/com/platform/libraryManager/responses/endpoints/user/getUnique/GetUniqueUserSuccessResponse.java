package com.platform.libraryManager.responses.endpoints.user.getUnique;

import com.platform.libraryManager.models.User;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueUserSuccessResponse extends GetUniqueUserResponse implements ISuccessResponse {

    public GetUniqueUserSuccessResponse(User user) {
        super(200, "success", ResponseType.SUCCESS);
        setUser(user);
    }

}
