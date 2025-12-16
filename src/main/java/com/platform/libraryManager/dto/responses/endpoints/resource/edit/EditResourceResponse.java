package com.platform.libraryManager.dto.responses.endpoints.resource.edit;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class EditResourceResponse extends Response {


    public EditResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
