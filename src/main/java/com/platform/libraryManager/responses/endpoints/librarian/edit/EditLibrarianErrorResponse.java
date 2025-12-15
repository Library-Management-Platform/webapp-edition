package com.platform.libraryManager.responses.endpoints.librarian.edit;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditLibrarianErrorResponse extends EditLibrarianResponse implements IErrorResponse {

    public EditLibrarianErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
