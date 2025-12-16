package com.platform.libraryManager.dto.responses.endpoints.librarian.remove;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class RemoveLibrarianSuccessResponse extends RemoveLibrarianResponse implements ISuccessResponse {

    public RemoveLibrarianSuccessResponse() {
        super(200, "Librarian has been removed successfully", ResponseType.SUCCESS);
    }
}
