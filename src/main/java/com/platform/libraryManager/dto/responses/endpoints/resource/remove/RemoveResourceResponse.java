package com.platform.libraryManager.dto.responses.endpoints.resource.remove;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class RemoveResourceResponse extends Response {


    public RemoveResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
