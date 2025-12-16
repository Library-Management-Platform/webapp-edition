package com.platform.libraryManager.dto.responses.endpoints.admin.getUnique;

import com.platform.libraryManager.utils.factories.AdminFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueAdminErrorResponse extends GetUniqueAdminResponse implements IErrorResponse {


    public GetUniqueAdminErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setAdmin(AdminFactory.createEmpty());
    }



}
