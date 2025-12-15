package com.platform.libraryManager.responses.endpoints.librarian.add;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddLibrarianErrorResponse extends AddLibrarianResponse implements IErrorResponse {

    public AddLibrarianErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
