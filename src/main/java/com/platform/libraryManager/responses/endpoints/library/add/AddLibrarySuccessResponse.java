package com.platform.libraryManager.responses.endpoints.library.add;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddLibrarySuccessResponse extends AddLibraryResponse implements ISuccessResponse {

    public AddLibrarySuccessResponse() {
        super(201, "Library has been added successfully", ResponseType.SUCCESS);
    }
}
