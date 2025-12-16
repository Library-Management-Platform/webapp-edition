package com.platform.libraryManager.dto.responses.endpoints.library.remove;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveLibrarySuccessResponse extends RemoveLibraryResponse implements ISuccessResponse {

    public RemoveLibrarySuccessResponse() {
        super(200, "Library has been removed successfully", ResponseType.SUCCESS);
    }
}
