package com.platform.libraryManager.logic.managers.resource;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dto.responses.endpoints.librarian.getUnique.GetUniqueLibrarianResponse;
import com.platform.libraryManager.dto.searchQueryParams.LibrarianSearchQueryParams;
import com.platform.libraryManager.logic.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AddResourceManager {

    @Autowired private LibrarianService librarianService;


    public Librarian getResourceLibrarian() {

        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        final GetUniqueLibrarianResponse getUniqueLibrarianResponse = librarianService.getUniqueLibrarian(
                new LibrarianSearchQueryParams(null, username, null, null, null, null)
        );

        return getUniqueLibrarianResponse.getLibrarian();

    }
}
