package com.platform.libraryManager.dto.responses.endpoints.client.getUnique;

import com.platform.libraryManager.dataAccess.models.Client;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class GetUniqueClientResponse extends Response {

    private Client client;

    public GetUniqueClientResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

}
