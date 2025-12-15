package com.platform.libraryManager.services;


import com.platform.libraryManager.factories.LibrarianFactory;
import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.payloads.librarian.AddLibrarianPayload;
import com.platform.libraryManager.payloads.librarian.EditLibrarianPayload;
import com.platform.libraryManager.providers.PasswordHashingProvider;
import com.platform.libraryManager.repositories.LibrarianRepository;
import com.platform.libraryManager.responses.endpoints.librarian.add.AddLibrarianErrorResponse;
import com.platform.libraryManager.responses.endpoints.librarian.add.AddLibrarianResponse;
import com.platform.libraryManager.responses.endpoints.librarian.add.AddLibrarianSuccessResponse;
import com.platform.libraryManager.responses.endpoints.librarian.edit.EditLibrarianErrorResponse;
import com.platform.libraryManager.responses.endpoints.librarian.edit.EditLibrarianResponse;
import com.platform.libraryManager.responses.endpoints.librarian.edit.EditLibrarianSuccessResponse;
import com.platform.libraryManager.responses.endpoints.librarian.getAll.GetAllLibrariansResponse;
import com.platform.libraryManager.responses.endpoints.librarian.getAll.GetAllLibrariansSuccessResponse;
import com.platform.libraryManager.responses.endpoints.librarian.getUnique.GetUniqueLibrarianErrorResponse;
import com.platform.libraryManager.responses.endpoints.librarian.getUnique.GetUniqueLibrarianResponse;
import com.platform.libraryManager.responses.endpoints.librarian.getUnique.GetUniqueLibrarianSuccessResponse;
import com.platform.libraryManager.responses.endpoints.librarian.remove.RemoveLibrarianErrorResponse;
import com.platform.libraryManager.responses.endpoints.librarian.remove.RemoveLibrarianResponse;
import com.platform.libraryManager.responses.endpoints.librarian.remove.RemoveLibrarianSuccessResponse;
import com.platform.libraryManager.searchQueryParams.LibrarianSearchQueryParams;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {

    @Autowired private LibraryService libraryService;
    @Autowired private LibrarianRepository librarianRepository;
    @Autowired private PasswordHashingProvider passwordHashingProvider;

    public AddLibrarianResponse addLibrarian(AddLibrarianPayload addLibrarianPayload) {

        try {

            final Librarian librarian = LibrarianFactory.create(
                    addLibrarianPayload,
                    libraryService.getUniqueLibrary(new LibrarySearchQueryParams(addLibrarianPayload.getLibraryId(), null, null, null, null)).getLibrary()
            );

            librarian.setPassword(passwordHashingProvider.hash(addLibrarianPayload.getUsername()));
            librarianRepository.save(librarian);
            return new AddLibrarianSuccessResponse();

        }catch(DataIntegrityViolationException dataIntegrityViolationException) {
            return new AddLibrarianErrorResponse(409, "Librarian already exists");

        } catch(Exception exception) {
            return new AddLibrarianErrorResponse(400, "Error");

        }
    }

    public EditLibrarianResponse editLibrarian(Long id, EditLibrarianPayload editLibrarianPayload) {

        try {

            final Librarian librarian = LibrarianFactory.create(
                    id,
                    editLibrarianPayload,
                    libraryService.getUniqueLibrary(new LibrarySearchQueryParams(editLibrarianPayload.getLibraryId(), null, null, null, null)).getLibrary()
            );

            librarianRepository.save(librarian);
            return new EditLibrarianSuccessResponse();

        }catch(DataIntegrityViolationException dataIntegrityViolationException) {
            return new EditLibrarianErrorResponse(409, "A Librarian with the same username already exists");

        } catch(Exception exception) {
            return new EditLibrarianErrorResponse(400, "Error");

        }
    }


    public RemoveLibrarianResponse removeLibrarian(Long id) {

        try {
            final Librarian librarian = getUniqueLibrarian(new LibrarianSearchQueryParams(id, null, null, null, null, null)).getLibrarian();
            librarianRepository.delete(librarian);
            return new RemoveLibrarianSuccessResponse();

        }catch (Exception exception) {
            return new RemoveLibrarianErrorResponse(400, "Error");
        }
    }

    public GetAllLibrariansResponse getAllLibrarians() {
        final List<Librarian> librarians = librarianRepository.findAll();
        return new GetAllLibrariansSuccessResponse(librarians);
    }


    public GetUniqueLibrarianResponse getUniqueLibrarian(LibrarianSearchQueryParams librarianSearchQueryParams) {

        try {
            final Librarian librarian = librarianRepository.searchOne(librarianSearchQueryParams).get();
            return new GetUniqueLibrarianSuccessResponse(librarian);

        }catch (Exception exception) {
            return new GetUniqueLibrarianErrorResponse();
        }

    }
}
