package com.platform.libraryManager.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class GetUniqueResourceResponse extends Response {

    private Resource resource;

    public GetUniqueResourceResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }

}
