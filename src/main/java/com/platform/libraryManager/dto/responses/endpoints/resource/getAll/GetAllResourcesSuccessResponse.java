package com.platform.libraryManager.dto.responses.endpoints.resource.getAll;

import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.List;

public class GetAllResourcesSuccessResponse extends GetAllResourcesResponse implements ISuccessResponse {


    public GetAllResourcesSuccessResponse(List<Resource> resources) {
        super(200, "Admins list", ResponseType.SUCCESS);
        setResources(resources);
    }


}
