package com.platform.libraryManager.responses.endpoints.resource.remove;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class RemoveResourceErrorResponse extends RemoveResourceResponse implements IErrorResponse {

    public RemoveResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
