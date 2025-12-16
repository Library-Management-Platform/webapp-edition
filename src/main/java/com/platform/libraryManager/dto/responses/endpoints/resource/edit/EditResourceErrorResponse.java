package com.platform.libraryManager.dto.responses.endpoints.resource.edit;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditResourceErrorResponse extends EditResourceResponse implements IErrorResponse {

    public EditResourceErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
