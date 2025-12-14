package com.platform.libraryManager.responses.endpoints.user.getUnique;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.responses.ResponseType;

public class GetUniqueUserErrorResponse extends GetUniqueUserResponse {


    public GetUniqueUserErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setUser(ClientFactory.createEmpty());
    }

    @Override public boolean success() { return false; }


}
