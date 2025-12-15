package com.platform.libraryManager.responses.endpoints.library.add;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class AddLibraryResponse extends Response {


    public AddLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
