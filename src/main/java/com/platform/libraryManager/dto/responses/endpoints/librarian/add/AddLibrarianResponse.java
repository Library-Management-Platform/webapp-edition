package com.platform.libraryManager.dto.responses.endpoints.librarian.add;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class AddLibrarianResponse extends Response {


    public AddLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
