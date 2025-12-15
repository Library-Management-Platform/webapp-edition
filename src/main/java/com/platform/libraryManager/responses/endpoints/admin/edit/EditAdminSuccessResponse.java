package com.platform.libraryManager.responses.endpoints.admin.edit;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditAdminSuccessResponse extends EditAdminResponse implements ISuccessResponse {

    public EditAdminSuccessResponse() {
        super(200, "Admin has been edited successfully", ResponseType.SUCCESS);
    }
}
