package com.platform.libraryManager.responses.endpoints.librarian.getUnique;

import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueLibrarianSuccessResponse extends GetUniqueLibrarianResponse implements ISuccessResponse {

    public GetUniqueLibrarianSuccessResponse(Librarian librarian) {
        super(200, "success", ResponseType.SUCCESS);
        setLibrarian(librarian);
    }

}
