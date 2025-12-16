package com.platform.libraryManager.dto.responses.endpoints.library.add;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class AddLibraryResponse extends Response {


    public AddLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
