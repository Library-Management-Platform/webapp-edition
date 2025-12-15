package com.platform.libraryManager.responses.endpoints.library.remove;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class RemoveLibraryResponse extends Response {


    public RemoveLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
