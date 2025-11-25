package com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.genericResponses.successResponse.SuccessResponse;

import java.util.List;

public class GetUniqueClientSuccessResponse extends SuccessResponse implements GetUniqueClientResponse {

    private Client client;

    public GetUniqueClientSuccessResponse(Client client) {
        super(200, "success");
        setClients(client);
    }

    public Client getClient() { return client; }
    private void setClients(Client client) { this.client = client; }
}
