package com.platform.libraryManager.dto.responses.endpoints.resource.remove;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveResourceErrorResponse extends RemoveResourceResponse implements IErrorResponse {

    public RemoveResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
