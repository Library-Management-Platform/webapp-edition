package com.platform.libraryManager.dto.responses.endpoints.admin.add;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class AddAdminResponse extends Response {


    public AddAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
