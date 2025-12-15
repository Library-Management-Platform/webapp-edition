package com.platform.libraryManager.responses.endpoints.admin.remove;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class RemoveAdminResponse extends Response {


    public RemoveAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
