package com.platform.libraryManager.responses.endpoints.librarian.remove;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class RemoveLibrarianResponse extends Response {


    public RemoveLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
