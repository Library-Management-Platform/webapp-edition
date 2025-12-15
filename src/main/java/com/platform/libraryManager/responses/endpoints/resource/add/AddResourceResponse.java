package com.platform.libraryManager.responses.endpoints.resource.add;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class AddResourceResponse extends Response {


    public AddResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
