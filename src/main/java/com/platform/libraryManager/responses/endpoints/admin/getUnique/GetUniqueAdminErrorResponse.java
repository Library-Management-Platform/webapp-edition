package com.platform.libraryManager.responses.endpoints.admin.getUnique;

import com.platform.libraryManager.factories.AdminFactory;
import com.platform.libraryManager.factories.LibraryFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueAdminErrorResponse extends GetUniqueAdminResponse implements IErrorResponse {


    public GetUniqueAdminErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setAdmin(AdminFactory.createEmpty());
    }



}
