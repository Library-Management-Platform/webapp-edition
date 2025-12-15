package com.platform.libraryManager.responses.endpoints.resource.edit;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class EditResourceResponse extends Response {


    public EditResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
