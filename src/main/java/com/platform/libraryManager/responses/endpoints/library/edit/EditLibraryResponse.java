package com.platform.libraryManager.responses.endpoints.library.edit;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class EditLibraryResponse extends Response {


    public EditLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
