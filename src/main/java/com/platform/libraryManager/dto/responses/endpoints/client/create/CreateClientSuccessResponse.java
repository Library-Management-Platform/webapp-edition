package com.platform.libraryManager.dto.responses.endpoints.client.create;

import com.platform.libraryManager.dto.responses.types.ISuccessResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class CreateClientSuccessResponse extends CreateClientResponse implements ISuccessResponse {

    public CreateClientSuccessResponse() {
        super(201, "Client Account has been created successfully", ResponseType.SUCCESS);
    }

}
