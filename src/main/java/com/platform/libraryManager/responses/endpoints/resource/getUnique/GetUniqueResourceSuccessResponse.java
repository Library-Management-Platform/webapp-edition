package com.platform.libraryManager.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueResourceSuccessResponse extends GetUniqueResourceResponse implements ISuccessResponse {

    public GetUniqueResourceSuccessResponse(Resource resource) {
        super(200, "success", ResponseType.SUCCESS);
        setResource(resource);
    }

}
