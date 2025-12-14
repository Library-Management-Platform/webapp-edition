package com.platform.libraryManager.responses.endpoints.user.getUnique;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class GetUniqueUserErrorResponse extends GetUniqueUserResponse implements IErrorResponse {


    public GetUniqueUserErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setUser(ClientFactory.createEmpty());
    }



}
