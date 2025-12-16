package com.platform.libraryManager.dto.responses.endpoints.client.create;

import com.platform.libraryManager.dto.responses.types.IErrorResponse;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public class CreateClientErrorResponse extends CreateClientResponse implements IErrorResponse {

    public CreateClientErrorResponse(int code, String message) { super(code, message, ResponseType.ERROR); }

}
