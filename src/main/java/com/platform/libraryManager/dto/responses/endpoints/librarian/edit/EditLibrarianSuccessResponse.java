package com.platform.libraryManager.dto.responses.endpoints.librarian.edit;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class EditLibrarianSuccessResponse extends EditLibrarianResponse implements ISuccessResponse {

    public EditLibrarianSuccessResponse() {
        super(200, "Librarian has been edited successfully", ResponseType.SUCCESS);
    }
}