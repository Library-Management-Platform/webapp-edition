package com.platform.libraryManager.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.factories.AdminFactory;
import com.platform.libraryManager.factories.ResourceFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueResourceErrorResponse extends GetUniqueResourceResponse implements IErrorResponse {


    public GetUniqueResourceErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setResource(ResourceFactory.createEmpty());
    }



}
