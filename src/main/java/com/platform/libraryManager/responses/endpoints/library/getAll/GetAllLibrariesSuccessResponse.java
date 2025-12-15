package com.platform.libraryManager.responses.endpoints.library.getAll;

import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

import java.util.List;

public class GetAllLibrariesSuccessResponse extends GetAllLibrariesResponse implements ISuccessResponse {


    public GetAllLibrariesSuccessResponse(List<Library> libraries) {
        super(200, "Libraries list", ResponseType.SUCCESS);
        setLibraries(libraries);
    }


}
