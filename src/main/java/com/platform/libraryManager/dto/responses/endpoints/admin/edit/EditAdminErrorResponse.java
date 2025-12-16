package com.platform.libraryManager.dto.responses.endpoints.admin.edit;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditAdminErrorResponse extends EditAdminResponse implements IErrorResponse {

    public EditAdminErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
