package com.platform.libraryManager.dto.responses.endpoints.resource.getAll;

import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.ArrayList;
import java.util.List;

public abstract class GetAllResourcesResponse extends Response {

    private List<Resource> resources;

    public GetAllResourcesResponse(
            int code,
            String message,
            ResponseType type
    ) {
        super(code, message, type);
        setResources(new ArrayList<>());
    }

    public List<Resource> getResources() { return resources; }
    public void setResources(List<Resource> resources) { this.resources = resources; }
}
