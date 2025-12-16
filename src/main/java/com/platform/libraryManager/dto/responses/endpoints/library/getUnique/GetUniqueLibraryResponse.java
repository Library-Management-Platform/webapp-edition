package com.platform.libraryManager.dto.responses.endpoints.library.getUnique;

import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class GetUniqueLibraryResponse extends Response {

    private Library library;

    public GetUniqueLibraryResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Library getLibrary() { return library; }
    public void setLibrary(Library library) { this.library = library; }

}
