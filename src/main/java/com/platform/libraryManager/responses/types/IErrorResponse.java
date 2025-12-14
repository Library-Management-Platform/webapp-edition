package com.platform.libraryManager.responses.types;

public interface IErrorResponse extends IResponse {

    default boolean success() { return false; }

}
