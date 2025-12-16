package com.platform.libraryManager.dto.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueResourceSuccessResponse extends GetUniqueResourceResponse implements ISuccessResponse {

    public GetUniqueResourceSuccessResponse(Resource resource) {
        super(200, "success", ResponseType.SUCCESS);
        setResource(resource);
    }

}
