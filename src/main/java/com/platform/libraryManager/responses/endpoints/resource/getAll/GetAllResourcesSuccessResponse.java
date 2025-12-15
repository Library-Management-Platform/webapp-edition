package com.platform.libraryManager.responses.endpoints.resource.getAll;

import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

import java.util.List;

public class GetAllResourcesSuccessResponse extends GetAllResourcesResponse implements ISuccessResponse {


    public GetAllResourcesSuccessResponse(List<Resource> resources) {
        super(200, "Admins list", ResponseType.SUCCESS);
        setResources(resources);
    }


}
