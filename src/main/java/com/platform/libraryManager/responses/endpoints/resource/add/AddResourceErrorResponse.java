package com.platform.libraryManager.responses.endpoints.resource.add;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddResourceErrorResponse extends AddResourceResponse implements IErrorResponse {

    public AddResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
