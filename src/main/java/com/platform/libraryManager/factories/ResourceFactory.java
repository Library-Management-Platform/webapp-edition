package com.platform.libraryManager.factories;

import com.platform.libraryManager.helpers.DateHelper;
import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.payloads.librarian.EditLibrarianPayload;
import com.platform.libraryManager.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.payloads.resource.EditResourcePayload;

public abstract class ResourceFactory {

    public static Resource createEmpty() { return new Resource(); }

    public static Resource create(AddResourcePayload addResourcePayload, Library library) {

        return new Resource(
                library,
                addResourcePayload.getTitle(),
                addResourcePayload.getAuthor(),
                addResourcePayload.getCategory(),
                addResourcePayload.getType()
        );
    }


    public static Resource create(Long id, EditResourcePayload editResourcePayload, Library library) {

        return new Resource(
                id,
                library,
                editResourcePayload.getTitle(),
                editResourcePayload.getAuthor(),
                editResourcePayload.getCategory(),
                editResourcePayload.getType(),
                editResourcePayload.getStatus()
        );
    }
}



