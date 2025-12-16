package com.platform.libraryManager.dto.responses.endpoints.librarian.remove;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class RemoveLibrarianResponse extends Response {


    public RemoveLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
