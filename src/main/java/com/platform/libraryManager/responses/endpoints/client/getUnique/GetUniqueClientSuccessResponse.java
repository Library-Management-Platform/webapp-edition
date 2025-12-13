package com.platform.libraryManager.responses.endpoints.client.getUnique;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.ResponseType;

public class GetUniqueClientSuccessResponse extends GetUniqueClientResponse {

    public GetUniqueClientSuccessResponse(Client client) {
        super(200, "success", ResponseType.SUCCESS);
        setClient(client);
    }

    @Override public boolean success() { return true; }

}
