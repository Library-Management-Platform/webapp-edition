package com.platform.libraryManager.responses.endpoints.librarian.add;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class AddLibrarianResponse extends Response {


    public AddLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
