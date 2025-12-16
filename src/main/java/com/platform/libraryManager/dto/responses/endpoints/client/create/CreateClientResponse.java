package com.platform.libraryManager.dto.responses.endpoints.client.create;

import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class CreateClientResponse extends Response {

    public CreateClientResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

}
