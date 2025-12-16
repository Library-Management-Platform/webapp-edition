package com.platform.libraryManager.dto.responses.types;

public interface IErrorResponse extends IResponse {

    default boolean success() { return false; }

}
