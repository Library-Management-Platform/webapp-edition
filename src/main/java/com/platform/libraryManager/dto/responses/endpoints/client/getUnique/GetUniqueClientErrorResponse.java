package com.platform.libraryManager.dto.responses.endpoints.client.getUnique;

import com.platform.libraryManager.shared.factories.ClientFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueClientErrorResponse extends GetUniqueClientResponse implements IErrorResponse {


    public GetUniqueClientErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setClient(ClientFactory.createEmpty());
    }



}
