package com.platform.libraryManager.responses.genericResponses.errorResponse;

import com.platform.libraryManager.responses.genericResponses.response.Response;

public abstract class ErrorResponse extends Response {

    public ErrorResponse(int code, String message) { super(code, message); }

    @Override public boolean success() { return false; }
}
