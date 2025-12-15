package com.platform.libraryManager.responses.endpoints.admin.add;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddAdminErrorResponse extends AddAdminResponse implements IErrorResponse {

    public AddAdminErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
