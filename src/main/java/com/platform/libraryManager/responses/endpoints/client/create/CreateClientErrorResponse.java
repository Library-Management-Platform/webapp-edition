package com.platform.libraryManager.responses.endpoints.client.create;

import com.platform.libraryManager.responses.types.IErrorResponse;
import com.platform.libraryManager.responses.types.ResponseType;

public class CreateClientErrorResponse extends CreateClientResponse implements IErrorResponse {

    public CreateClientErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
