package com.platform.libraryManager.dto.responses.endpoints.library.edit;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class EditLibraryResponse extends Response {


    public EditLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }
}
