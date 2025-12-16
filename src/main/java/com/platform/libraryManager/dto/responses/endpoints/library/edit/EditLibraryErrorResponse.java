package com.platform.libraryManager.dto.responses.endpoints.library.edit;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditLibraryErrorResponse extends EditLibraryResponse implements IErrorResponse {

    public EditLibraryErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
