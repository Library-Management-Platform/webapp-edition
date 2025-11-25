package com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses;

import com.platform.libraryManager.responses.genericResponses.successResponse.SuccessResponse;

public class CreateClientSuccessResponse extends SuccessResponse implements CreateClientResponse{

    public CreateClientSuccessResponse() {
        super(
                201,
                "Client has been created successfully"
        );
    }
}
