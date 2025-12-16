package com.platform.libraryManager.dto.responses.endpoints.librarian.edit;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class EditLibrarianResponse extends Response {


    public EditLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
