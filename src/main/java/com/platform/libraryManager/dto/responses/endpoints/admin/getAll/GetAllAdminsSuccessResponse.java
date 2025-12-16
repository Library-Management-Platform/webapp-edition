package com.platform.libraryManager.dto.responses.endpoints.admin.getAll;

import com.platform.libraryManager.dataAccess.models.Admin;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.List;

public class GetAllAdminsSuccessResponse extends GetAllAdminsResponse implements ISuccessResponse {


    public GetAllAdminsSuccessResponse(List<Admin> admins) {
        super(200, "Admins list", ResponseType.SUCCESS);
        setAdmins(admins);
    }


}
