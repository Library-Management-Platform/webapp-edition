package com.platform.libraryManager.responses.endpoints.librarian.getAll;

import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

import java.util.List;

public class GetAllLibrariansSuccessResponse extends GetAllLibrariansResponse implements ISuccessResponse {


    public GetAllLibrariansSuccessResponse(List<Librarian> librarians) {
        super(200, "Librarians list", ResponseType.SUCCESS);
        setLibrarians(librarians);
    }


}
