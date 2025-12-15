package com.platform.libraryManager.responses.endpoints.library.getAll;

import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

import java.util.ArrayList;
import java.util.List;

public abstract class GetAllLibrariesResponse extends Response {

    private List<Library> libraries;

    public GetAllLibrariesResponse(
            int code,
            String message,
            ResponseType type
    ) {
        super(code, message, type);
        setLibraries(new ArrayList<>());
    }

    public List<Library> getLibraries() { return libraries; }
    public void setLibraries(List<Library> libraries) { this.libraries = libraries; }
}
