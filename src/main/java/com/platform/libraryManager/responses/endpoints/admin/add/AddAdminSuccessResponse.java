package com.platform.libraryManager.responses.endpoints.admin.add;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddAdminSuccessResponse extends AddAdminResponse implements ISuccessResponse {

    public AddAdminSuccessResponse() {
        super(201, "Admin has been added successfully", ResponseType.SUCCESS);
    }
}
