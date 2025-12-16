package com.platform.libraryManager.dto.responses.endpoints.admin.edit;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class EditAdminResponse extends Response {


    public EditAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
