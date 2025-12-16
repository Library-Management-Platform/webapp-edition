package com.platform.libraryManager.dto.responses.endpoints.admin.edit;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditAdminSuccessResponse extends EditAdminResponse implements ISuccessResponse {

    public EditAdminSuccessResponse() {
        super(200, "Admin has been edited successfully", ResponseType.SUCCESS);
    }
}