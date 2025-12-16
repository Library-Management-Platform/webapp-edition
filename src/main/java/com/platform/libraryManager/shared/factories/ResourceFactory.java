package com.platform.libraryManager.shared.factories;

import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dto.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.dto.payloads.resource.EditResourcePayload;

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



