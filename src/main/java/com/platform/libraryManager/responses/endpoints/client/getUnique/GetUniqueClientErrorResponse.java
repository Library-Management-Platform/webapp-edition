package com.platform.libraryManager.responses.endpoints.client.getUnique;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.responses.ResponseType;

public class GetUniqueClientErrorResponse extends GetUniqueClientResponse {


    public GetUniqueClientErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setClient(ClientFactory.createEmpty());
    }

    @Override public boolean success() { return false; }


}
