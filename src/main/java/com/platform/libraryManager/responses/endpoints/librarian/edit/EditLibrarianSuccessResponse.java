package com.platform.libraryManager.responses.endpoints.librarian.edit;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditLibrarianSuccessResponse extends EditLibrarianResponse implements ISuccessResponse {

    public EditLibrarianSuccessResponse() {
        super(200, "Librarian has been edited successfully", ResponseType.SUCCESS);
    }
}