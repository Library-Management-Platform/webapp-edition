package com.platform.libraryManager.dto.responses.endpoints.resource.getUnique;

import com.platform.libraryManager.utils.factories.ResourceFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueResourceErrorResponse extends GetUniqueResourceResponse implements IErrorResponse {


    public GetUniqueResourceErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setResource(ResourceFactory.createEmpty());
    }



}
