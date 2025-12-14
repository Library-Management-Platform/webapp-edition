package com.platform.libraryManager.responses.endpoints.client.create;

import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class CreateClientResponse extends Response {

    public CreateClientResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
