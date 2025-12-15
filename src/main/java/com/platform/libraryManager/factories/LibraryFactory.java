package com.platform.libraryManager.factories;

import com.platform.libraryManager.helpers.DateHelper;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.payloads.library.AddLibraryPayload;
import com.platform.libraryManager.payloads.library.EditLibraryPayload;

import java.util.ArrayList;

public abstract class LibraryFactory {

    public static Library createEmpty() { return new Library(); }

    public static Library create(AddLibraryPayload addLibraryPayload) {

        return new Library(
                addLibraryPayload.getName(),
                addLibraryPayload.getAddress(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime()
        );
    }


    public static Library create(Long id, EditLibraryPayload editLibraryPayload) {

        return new Library(
                id,
                editLibraryPayload.getName(),
                editLibraryPayload.getAddress(),
                new ArrayList<>()
        );
    }
}
