package com.platform.libraryManager.dto.responses.endpoints.library.add;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddLibraryErrorResponse extends AddLibraryResponse implements IErrorResponse {

    public AddLibraryErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
