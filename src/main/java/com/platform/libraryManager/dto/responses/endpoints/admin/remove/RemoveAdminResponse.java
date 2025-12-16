package com.platform.libraryManager.dto.responses.endpoints.admin.remove;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class RemoveAdminResponse extends Response {


    public RemoveAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
