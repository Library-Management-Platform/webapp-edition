package com.platform.libraryManager.responses.endpoints.library.getUnique;

import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueLibrarySuccessResponse extends GetUniqueLibraryResponse implements ISuccessResponse {

    public GetUniqueLibrarySuccessResponse(Library library) {
        super(200, "success", ResponseType.SUCCESS);
        setLibrary(library);
    }

}
