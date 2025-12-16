package com.platform.libraryManager.dto.responses.endpoints.admin.add;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddAdminSuccessResponse extends AddAdminResponse implements ISuccessResponse {

    public AddAdminSuccessResponse() {
        super(201, "Admin has been added successfully", ResponseType.SUCCESS);
    }
}
