package com.platform.libraryManager.dto.responses.endpoints.resource.add;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class AddResourceResponse extends Response {


    public AddResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
