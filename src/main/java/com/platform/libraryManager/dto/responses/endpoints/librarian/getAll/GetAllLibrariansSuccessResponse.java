package com.platform.libraryManager.dto.responses.endpoints.librarian.getAll;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.List;

public class GetAllLibrariansSuccessResponse extends GetAllLibrariansResponse implements ISuccessResponse {


    public GetAllLibrariansSuccessResponse(List<Librarian> librarians) {
        super(200, "Librarians list", ResponseType.SUCCESS);
        setLibrarians(librarians);
    }


}
