package com.platform.libraryManager.responses.endpoints.library.remove;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class RemoveLibrarySuccessResponse extends RemoveLibraryResponse implements ISuccessResponse {

    public RemoveLibrarySuccessResponse() {
        super(200, "Library has been removed successfully", ResponseType.SUCCESS);
    }
}
