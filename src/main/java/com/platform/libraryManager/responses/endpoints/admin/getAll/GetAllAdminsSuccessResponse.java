package com.platform.libraryManager.responses.endpoints.admin.getAll;

import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

import java.util.List;

public class GetAllAdminsSuccessResponse extends GetAllAdminsResponse implements ISuccessResponse {


    public GetAllAdminsSuccessResponse(List<Admin> admins) {
        super(200, "Admins list", ResponseType.SUCCESS);
        setAdmins(admins);
    }


}
