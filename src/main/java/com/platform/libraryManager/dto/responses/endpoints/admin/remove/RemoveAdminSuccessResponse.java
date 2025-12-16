package com.platform.libraryManager.dto.responses.endpoints.admin.remove;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveAdminSuccessResponse extends RemoveAdminResponse implements ISuccessResponse {

    public RemoveAdminSuccessResponse() {
        super(200, "Admin has been removed successfully", ResponseType.SUCCESS);
    }
}
