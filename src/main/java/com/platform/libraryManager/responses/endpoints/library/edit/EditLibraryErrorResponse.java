package com.platform.libraryManager.responses.endpoints.library.edit;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditLibraryErrorResponse extends EditLibraryResponse implements IErrorResponse {

    public EditLibraryErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
