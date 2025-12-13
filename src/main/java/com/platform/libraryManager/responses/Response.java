package com.platform.libraryManager.responses;

public abstract class Response {

    private int code;
    private String message;
    private ResponseType type;


    public Response(
            int code,
            String message,
            ResponseType type
    ) {
        setCode(code);
        setMessage(message);
        setType(type);
    }

    public int getCode() { return code; }
    private void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }
    private void setMessage(String message) { this.message = message; }

    public ResponseType getType() { return type; }
    public void setType(ResponseType type) { this.type = type; }

    public abstract boolean success();
}
