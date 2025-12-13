package com.platform.libraryManager.responses.endpoints.client.getUnique;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.ResponseType;

public abstract class GetUniqueClientResponse extends Response {

    private Client client;

    public GetUniqueClientResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

}
