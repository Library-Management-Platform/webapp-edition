package com.platform.libraryManager.dto.responses.endpoints.admin.add;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddAdminErrorResponse extends AddAdminResponse implements IErrorResponse {

    public AddAdminErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
