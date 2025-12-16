package com.platform.libraryManager.dto.responses.endpoints.library.edit;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditLibrarySuccessResponse extends EditLibraryResponse implements ISuccessResponse {

    public EditLibrarySuccessResponse() {
        super(200, "Library has been edited successfully", ResponseType.SUCCESS);
    }
}
