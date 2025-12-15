package com.platform.libraryManager.responses.endpoints.resource.add;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class AddResourceSuccessResponse extends AddResourceResponse implements ISuccessResponse {

    public AddResourceSuccessResponse() {
        super(201, "Resource has been added successfully", ResponseType.SUCCESS);
    }
}
