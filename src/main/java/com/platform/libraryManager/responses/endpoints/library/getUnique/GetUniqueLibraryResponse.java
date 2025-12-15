package com.platform.libraryManager.responses.endpoints.library.getUnique;

import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class GetUniqueLibraryResponse extends Response {

    private Library library;

    public GetUniqueLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }

}
