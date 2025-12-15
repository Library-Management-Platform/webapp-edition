package com.platform.libraryManager.responses.endpoints.library.edit;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditLibrarySuccessResponse extends EditLibraryResponse implements ISuccessResponse {

    public EditLibrarySuccessResponse() {
        super(200, "Library has been edited successfully", ResponseType.SUCCESS);
    }
}
