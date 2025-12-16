package com.platform.libraryManager.dto.responses.endpoints.library.remove;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class RemoveLibraryResponse extends Response {


    public RemoveLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
