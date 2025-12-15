package com.platform.libraryManager.responses.endpoints.library.remove;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class RemoveLibraryErrorResponse extends RemoveLibraryResponse implements IErrorResponse {

    public RemoveLibraryErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
