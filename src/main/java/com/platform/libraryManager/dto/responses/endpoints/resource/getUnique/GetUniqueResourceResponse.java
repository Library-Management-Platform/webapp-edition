package com.platform.libraryManager.dto.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class GetUniqueResourceResponse extends Response {

    private Resource resource;

    public GetUniqueResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }

}
