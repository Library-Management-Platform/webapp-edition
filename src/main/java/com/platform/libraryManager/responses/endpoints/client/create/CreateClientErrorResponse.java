package com.platform.libraryManager.responses.endpoints.client.create;

import com.platform.libraryManager.responses.ResponseType;

public class CreateClientErrorResponse extends CreateClientResponse {

    public CreateClientErrorResponse(int code, String message) { super(code, message, ResponseType.SUCCESS); }

    @Override public boolean success() { return false; }
}
