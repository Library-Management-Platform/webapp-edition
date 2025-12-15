package com.platform.libraryManager.responses.endpoints.admin.add;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class AddAdminResponse extends Response {


    public AddAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
