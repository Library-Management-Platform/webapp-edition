package com.platform.libraryManager.dto.responses.endpoints.librarian.getUnique;

import com.platform.libraryManager.shared.factories.LibrarianFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueLibrarianErrorResponse extends GetUniqueLibrarianResponse implements IErrorResponse {


    public GetUniqueLibrarianErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setLibrarian(LibrarianFactory.createEmpty());
    }



}
