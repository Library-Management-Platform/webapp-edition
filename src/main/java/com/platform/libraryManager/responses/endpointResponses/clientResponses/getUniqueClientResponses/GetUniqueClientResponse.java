package com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.genericResponses.response.IResponse;

public interface GetUniqueClientResponse extends IResponse {
    public Client getClient();
}
