package com.platform.libraryManager.dto.responses.endpoints.librarian.getUnique;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueLibrarianSuccessResponse extends GetUniqueLibrarianResponse implements ISuccessResponse {

    public GetUniqueLibrarianSuccessResponse(Librarian librarian) {
        super(200, "success", ResponseType.SUCCESS);
        setLibrarian(librarian);
    }

}
