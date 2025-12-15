package com.platform.libraryManager.responses.endpoints.librarian.getUnique;

import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.responses.Response;
import com.platform.libraryManager.responses.types.ResponseType;

public abstract class GetUniqueLibrarianResponse extends Response {

    private Librarian librarian;

    public GetUniqueLibrarianResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Librarian getLibrarian() { return librarian; }
    public void setLibrarian(Librarian librarian) { this.librarian = librarian; }

}
