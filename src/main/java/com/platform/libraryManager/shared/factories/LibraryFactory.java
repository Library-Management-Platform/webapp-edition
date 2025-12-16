package com.platform.libraryManager.shared.factories;

import com.platform.libraryManager.shared.helpers.DateHelper;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dto.payloads.library.AddLibraryPayload;
import com.platform.libraryManager.dto.payloads.library.EditLibraryPayload;

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
