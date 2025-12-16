package com.platform.libraryManager.dto.responses.endpoints.user.getUnique;

import com.platform.libraryManager.shared.factories.ClientFactory;
import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class GetUniqueUserErrorResponse extends GetUniqueUserResponse implements IErrorResponse {


    public GetUniqueUserErrorResponse() {
        super(404, "not found", ResponseType.ERROR);
        setUser(ClientFactory.createEmpty());
    }



}
