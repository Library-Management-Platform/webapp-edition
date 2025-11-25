package com.platform.libraryManager.responses.genericResponses.response;

public interface IResponse {

    public int getCode();
    public void setCode(int code);

    public String getMessage();
    public void setMessage(String message);

    public abstract boolean success();
}
