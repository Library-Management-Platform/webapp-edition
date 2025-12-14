package com.platform.libraryManager.responses.endpoints.client.getUnique;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.responses.types.ISuccessResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueClientSuccessResponse extends GetUniqueClientResponse implements ISuccessResponse {

    public GetUniqueClientSuccessResponse(Client client) {
        super(200, "success", ResponseType.SUCCESS);
        setClient(client);
    }

}
