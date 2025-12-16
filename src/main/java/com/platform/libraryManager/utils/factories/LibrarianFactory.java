package com.platform.libraryManager.utils.factories;

import com.platform.libraryManager.utils.helpers.DateHelper;
import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dto.payloads.librarian.AddLibrarianPayload;
import com.platform.libraryManager.dto.payloads.librarian.EditLibrarianPayload;

public abstract class LibrarianFactory {

    public static Librarian createEmpty() { return new Librarian(); }

    public static Librarian create(AddLibrarianPayload addLibrarianPayload, Library library) {

        return new Librarian(
                addLibrarianPayload.getUsername(),
                addLibrarianPayload.getEmail(),
                addLibrarianPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime(),
                library
        );
    }


    public static Librarian create(Long id, EditLibrarianPayload editLibrarianPayload, Library library) {

        return new Librarian(
                id,
                editLibrarianPayload.getUsername(),
                editLibrarianPayload.getEmail(),
                editLibrarianPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime(),
                library
        );
    }
}
