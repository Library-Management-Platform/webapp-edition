package com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class CreateClientExistsErrorResponse extends ErrorResponse implements CreateClientResponse{

    public CreateClientExistsErrorResponse() {
        super(
                409,
                "Client Already Exists"
        );
    }
}
