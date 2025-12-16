package com.platform.libraryManager.dto.responses.endpoints.library.add;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class AddLibrarySuccessResponse extends AddLibraryResponse implements ISuccessResponse {

    public AddLibrarySuccessResponse() {
        super(201, "Library has been added successfully", ResponseType.SUCCESS);
    }
}
