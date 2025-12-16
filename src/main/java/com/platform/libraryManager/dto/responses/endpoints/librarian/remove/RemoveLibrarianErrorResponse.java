package com.platform.libraryManager.dto.responses.endpoints.librarian.remove;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveLibrarianErrorResponse extends RemoveLibrarianResponse implements IErrorResponse {

    public RemoveLibrarianErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
