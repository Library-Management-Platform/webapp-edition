package com.platform.libraryManager.responses.endpoints.admin.edit;

import com.platform.libraryManager.responses.endpoints.librarian.edit.EditLibrarianResponse;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditAdminErrorResponse extends EditAdminResponse implements IErrorResponse {

    public EditAdminErrorResponse(int code, String message) {
        super(code, message, ResponseType.ERROR);
    }

}
