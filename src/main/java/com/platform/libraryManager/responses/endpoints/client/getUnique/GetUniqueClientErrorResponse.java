package com.platform.libraryManager.responses.endpoints.client.getUnique;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueClientErrorResponse extends GetUniqueClientResponse implements IErrorResponse {


    public GetUniqueClientErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setClient(ClientFactory.createEmpty());
    }



}
