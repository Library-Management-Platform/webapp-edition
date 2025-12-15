package com.platform.libraryManager.responses.endpoints.library.getUnique;

import com.platform.libraryManager.factories.LibraryFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueLibraryErrorResponse extends GetUniqueLibraryResponse implements IErrorResponse {


    public GetUniqueLibraryErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setLibrary(LibraryFactory.createEmpty());
    }



}
