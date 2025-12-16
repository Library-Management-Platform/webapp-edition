package com.platform.libraryManager.dto.responses.endpoints.library.getAll;

import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.List;

public class GetAllLibrariesSuccessResponse extends GetAllLibrariesResponse implements ISuccessResponse {


    public GetAllLibrariesSuccessResponse(List<Library> libraries) {
        super(200, "Libraries list", ResponseType.SUCCESS);
        setLibraries(libraries);
    }


}
