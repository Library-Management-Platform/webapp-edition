package com.platform.libraryManager.dto.responses.endpoints.admin.remove;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveAdminErrorResponse extends RemoveAdminResponse implements IErrorResponse {

    public RemoveAdminErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
