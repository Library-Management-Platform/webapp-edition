package com.platform.libraryManager.dto.responses.endpoints.librarian.edit;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditLibrarianErrorResponse extends EditLibrarianResponse implements IErrorResponse {

    public EditLibrarianErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
