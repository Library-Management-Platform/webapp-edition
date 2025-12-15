package com.platform.libraryManager.responses.endpoints.librarian.getUnique;

import com.platform.libraryManager.factories.LibrarianFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueLibrarianErrorResponse extends GetUniqueLibrarianResponse implements IErrorResponse {


    public GetUniqueLibrarianErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setLibrarian(LibrarianFactory.createEmpty());
    }



}
