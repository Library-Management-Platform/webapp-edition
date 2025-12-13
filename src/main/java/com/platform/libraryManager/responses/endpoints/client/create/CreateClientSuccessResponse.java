package com.platform.libraryManager.responses.endpoints.client.create;

import com.platform.libraryManager.responses.ResponseType;

public class CreateClientSuccessResponse extends CreateClientResponse {

    public CreateClientSuccessResponse() {
        super(201, "Client Account has been created successfully", ResponseType.SUCCESS);
    }

    @Override public boolean success() { return true; }
}
