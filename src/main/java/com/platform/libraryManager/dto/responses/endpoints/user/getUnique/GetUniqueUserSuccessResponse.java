package com.platform.libraryManager.dto.responses.endpoints.user.getUnique;

import com.platform.libraryManager.dataAccess.models.User;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueUserSuccessResponse extends GetUniqueUserResponse implements ISuccessResponse {

    public GetUniqueUserSuccessResponse(User user) {
        super(200, "success", ResponseType.SUCCESS);
        setUser(user);
    }

}
