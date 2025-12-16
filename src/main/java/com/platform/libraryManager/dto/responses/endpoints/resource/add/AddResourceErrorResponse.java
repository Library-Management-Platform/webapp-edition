package com.platform.libraryManager.dto.responses.endpoints.resource.add;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddResourceErrorResponse extends AddResourceResponse implements IErrorResponse {

    public AddResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
