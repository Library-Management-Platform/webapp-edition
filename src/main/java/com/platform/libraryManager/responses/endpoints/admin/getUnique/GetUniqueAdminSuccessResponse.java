package com.platform.libraryManager.responses.endpoints.admin.getUnique;

import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueAdminSuccessResponse extends GetUniqueAdminResponse implements ISuccessResponse {

    public GetUniqueAdminSuccessResponse(Admin admin) {
        super(200, "success", ResponseType.SUCCESS);
        setAdmin(admin);
    }

}
