package com.platform.libraryManager.responses.endpoints.librarian.edit;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class EditLibrarianResponse extends Response {


    public EditLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
