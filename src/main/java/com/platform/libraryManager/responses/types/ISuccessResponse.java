package com.platform.libraryManager.responses.types;

public interface ISuccessResponse extends IResponse {

    default boolean success() { return true; }
}
