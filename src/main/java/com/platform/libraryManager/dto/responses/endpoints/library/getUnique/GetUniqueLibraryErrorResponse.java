package com.platform.libraryManager.dto.responses.endpoints.library.getUnique;

import com.platform.libraryManager.utils.factories.LibraryFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueLibraryErrorResponse extends GetUniqueLibraryResponse implements IErrorResponse {


    public GetUniqueLibraryErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setLibrary(LibraryFactory.createEmpty());
    }



}
