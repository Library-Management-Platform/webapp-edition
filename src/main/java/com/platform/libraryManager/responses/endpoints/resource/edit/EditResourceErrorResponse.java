package com.platform.libraryManager.responses.endpoints.resource.edit;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditResourceErrorResponse extends EditResourceResponse implements IErrorResponse {

    public EditResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
