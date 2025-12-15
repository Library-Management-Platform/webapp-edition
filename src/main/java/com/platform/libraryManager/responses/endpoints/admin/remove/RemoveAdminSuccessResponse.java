package com.platform.libraryManager.responses.endpoints.admin.remove;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class RemoveAdminSuccessResponse extends RemoveAdminResponse implements ISuccessResponse {

    public RemoveAdminSuccessResponse() {
        super(200, "Admin has been removed successfully", ResponseType.SUCCESS);
    }
}
