package com.platform.libraryManager.dto.responses.endpoints.library.getUnique;

import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueLibrarySuccessResponse extends GetUniqueLibraryResponse implements ISuccessResponse {

    public GetUniqueLibrarySuccessResponse(Library library) {
        super(200, "success", ResponseType.SUCCESS);
        setLibrary(library);
    }

}
