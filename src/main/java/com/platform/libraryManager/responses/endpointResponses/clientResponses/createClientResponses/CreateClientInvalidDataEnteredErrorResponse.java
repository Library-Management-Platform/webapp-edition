package com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses;

import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;

public class CreateClientInvalidDataEnteredErrorResponse extends ErrorResponse implements CreateClientResponse{

    public CreateClientInvalidDataEnteredErrorResponse() {
        super(
                400,
                "Invalid Client data format."
        );
    }
}
