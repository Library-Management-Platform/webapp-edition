package com.platform.libraryManager.responses.endpoints.resource.edit;

import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class EditResourceSuccessResponse extends EditResourceResponse implements ISuccessResponse {

    public EditResourceSuccessResponse() {
        super(200, "Resource has been edited successfully", ResponseType.SUCCESS);
    }
}