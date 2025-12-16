package com.platform.libraryManager.services;

import com.platform.libraryManager.factories.LibraryFactory;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.payloads.library.AddLibraryPayload;
import com.platform.libraryManager.payloads.library.EditLibraryPayload;
import com.platform.libraryManager.repositories.LibraryRepository;
import com.platform.libraryManager.responses.endpoints.library.add.AddLibraryErrorResponse;
import com.platform.libraryManager.responses.endpoints.library.add.AddLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.add.AddLibrarySuccessResponse;
import com.platform.libraryManager.responses.endpoints.library.edit.EditLibraryErrorResponse;
import com.platform.libraryManager.responses.endpoints.library.edit.EditLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.edit.EditLibrarySuccessResponse;
import com.platform.libraryManager.responses.endpoints.library.getAll.GetAllLibrariesResponse;
import com.platform.libraryManager.responses.endpoints.library.getAll.GetAllLibrariesSuccessResponse;
import com.platform.libraryManager.responses.endpoints.library.getUnique.GetUniqueLibraryErrorResponse;
import com.platform.libraryManager.responses.endpoints.library.getUnique.GetUniqueLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.getUnique.GetUniqueLibrarySuccessResponse;
import com.platform.libraryManager.responses.endpoints.library.remove.RemoveLibraryErrorResponse;
import com.platform.libraryManager.responses.endpoints.library.remove.RemoveLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.remove.RemoveLibrarySuccessResponse;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public AddLibraryResponse addLibrary(AddLibraryPayload addLibraryPayload) {

        try {

            final Library library = LibraryFactory.create(addLibraryPayload);
            libraryRepository.save(library);
            return new AddLibrarySuccessResponse();

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return new AddLibraryErrorResponse(409, "Library already exists");

        } catch (Exception exception) {
            return new AddLibraryErrorResponse(400, "Error");

        }

    }

    public EditLibraryResponse editLibrary(Long id, EditLibraryPayload editLibraryPayload) {
        try {

            final Library library = LibraryFactory.create(id, editLibraryPayload);
            libraryRepository.save(library);
            return new EditLibrarySuccessResponse();

        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return new EditLibraryErrorResponse(409, "A Library with the same address already exists");

        } catch (Exception exception) {
            return new EditLibraryErrorResponse(400, "Error");

        }
    }

    public RemoveLibraryResponse removeLibrary(Long id) {

        try {
            final Library library = getUniqueLibrary(new LibrarySearchQueryParams(id, null, null, null, null))
                    .getLibrary();
            libraryRepository.delete(library);
            return new RemoveLibrarySuccessResponse();

        } catch (Exception exception) {
            return new RemoveLibraryErrorResponse(400, "Error");
        }
    }

    public GetAllLibrariesResponse getAllLibraries() {
        final List<Library> libraries = libraryRepository.findAll();
        return new GetAllLibrariesSuccessResponse(libraries);
    }

    public GetUniqueLibraryResponse getUniqueLibrary(LibrarySearchQueryParams librarySearchQueryParams) {

        try {
            final Library library = libraryRepository.searchOne(librarySearchQueryParams).get();
            return new GetUniqueLibrarySuccessResponse(library);

        } catch (Exception exception) {
            return new GetUniqueLibraryErrorResponse();
        }
    }
}
