package com.platform.libraryManager.responses.genericResponses.successResponse;

import com.platform.libraryManager.responses.genericResponses.response.Response;

public abstract class SuccessResponse extends Response {

    public SuccessResponse(int code, String message) { super(code, message); }

    @Override public boolean success() { return true; }
}
