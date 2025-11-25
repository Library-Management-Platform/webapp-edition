package com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.genericResponses.errorResponse.ErrorResponse;


public class GetUniqueClientErrorResponse extends ErrorResponse implements GetUniqueClientResponse {

    private Client client;

    public GetUniqueClientErrorResponse() {
        super(404, "not found");
        setClient(ClientFactory.createEmpty());
    }

    public Client getClient() { return client; }
    private void setClient(Client client) { this.client = client; }
}
