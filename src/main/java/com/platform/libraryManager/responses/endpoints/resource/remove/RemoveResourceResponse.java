package com.platform.libraryManager.responses.endpoints.resource.remove;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class RemoveResourceResponse extends Response {


    public RemoveResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
