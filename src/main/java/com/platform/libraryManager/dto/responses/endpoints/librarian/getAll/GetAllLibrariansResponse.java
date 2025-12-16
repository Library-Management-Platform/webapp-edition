package com.platform.libraryManager.dto.responses.endpoints.librarian.getAll;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.ArrayList;
import java.util.List;

public abstract class GetAllLibrariansResponse extends Response {

    private List<Librarian> librarians;

    public GetAllLibrariansResponse(
            int code,
            String message,
            ResponseType type
    ) {
        super(code, message, type);
        setLibrarians(new ArrayList<>());
    }

    public List<Librarian> getLibrarians() { return librarians; }
    public void setLibrarians(List<Librarian> librarians) { this.librarians = librarians; }
}
