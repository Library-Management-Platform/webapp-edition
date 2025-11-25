package com.platform.libraryManager.responses.genericResponses.response;

public abstract class Response implements IResponse{

    private int code;
    private String message;


    public Response(
            int code,
            String message
    ) {
        setCode(code);
        setMessage(message);
    }

    @Override  public int getCode() { return code; }
    @Override public void setCode(int code) { this.code = code; }

    @Override public String getMessage() { return message; }
    @Override public void setMessage(String message) { this.message = message; }

    public abstract boolean success();
}
