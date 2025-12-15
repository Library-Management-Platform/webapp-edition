package com.platform.libraryManager.responses.endpoints.admin.edit;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class EditAdminResponse extends Response {


    public EditAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
