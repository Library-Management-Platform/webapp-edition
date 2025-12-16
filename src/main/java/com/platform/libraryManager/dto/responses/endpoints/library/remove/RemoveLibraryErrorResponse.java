package com.platform.libraryManager.dto.responses.endpoints.library.remove;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveLibraryErrorResponse extends RemoveLibraryResponse implements IErrorResponse {

    public RemoveLibraryErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
