package com.platform.libraryManager.dto.responses.types;

public interface ISuccessResponse extends IResponse {

    default boolean success() { return true; }
}
