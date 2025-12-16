package com.platform.libraryManager.dto.responses.endpoints.librarian.add;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddLibrarianSuccessResponse extends AddLibrarianResponse implements ISuccessResponse {

    public AddLibrarianSuccessResponse() {
        super(201, "Librarian has been added successfully", ResponseType.SUCCESS);
    }
}
