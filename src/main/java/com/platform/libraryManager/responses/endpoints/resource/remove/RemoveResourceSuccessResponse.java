package com.platform.libraryManager.responses.endpoints.resource.remove;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class RemoveResourceSuccessResponse extends RemoveResourceResponse implements ISuccessResponse {

    public RemoveResourceSuccessResponse() {
        super(200, "Resource has been removed successfully", ResponseType.SUCCESS);
    }
}
