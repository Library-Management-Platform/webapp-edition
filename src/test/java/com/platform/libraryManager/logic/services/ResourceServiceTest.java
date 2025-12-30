package com.platform.libraryManager.logic.services;


import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dataAccess.repositories.ResourceRepository;
import com.platform.libraryManager.dto.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.dto.responses.endpoints.library.getUnique.GetUniqueLibrarySuccessResponse;
import com.platform.libraryManager.dto.responses.endpoints.resource.add.AddResourceResponse;
import com.platform.libraryManager.dto.searchQueryParams.LibrarySearchQueryParams;
import com.platform.libraryManager.logic.managers.resource.AddResourceManager;
import com.platform.libraryManager.shared.enums.ResourceCategoryEnum;
import com.platform.libraryManager.shared.enums.ResourceTypeEnum;
import com.platform.libraryManager.shared.providers.FilebaseIPFSProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class ResourceServiceTest {

    @InjectMocks private ResourceService resourceService;

    @Mock private LibraryService libraryService;
    @Mock private AddResourceManager addResourceManager;

    @Mock private ResourceRepository resourceRepository;
    @Mock private FilebaseIPFSProvider filebaseIPFSProvider;

    @Mock private MultipartFile multipartFile;

    private AddResourcePayload addResourcePayload;

    @BeforeEach
    void setup() {


        addResourcePayload = new AddResourcePayload(
                "resource1",
                "author1",
                ResourceCategoryEnum.ENTERTAINMENT,
                ResourceTypeEnum.BOOK,
                1L
        );
    }


    @Test
    void shouldReturnSuccessResponse_whenAddResourceIsSuccessful() {

        final Library fakeLibrary = new Library(1L, "name", "address", new ArrayList<>());
        final Librarian fakeLibrarian = new Librarian();
        fakeLibrarian.setLibrary(fakeLibrary);

        when(addResourceManager.getResourceLibrarian()).thenReturn(fakeLibrarian);
        when(libraryService.getUniqueLibrary(any(LibrarySearchQueryParams.class))).thenReturn(new GetUniqueLibrarySuccessResponse(fakeLibrary));

        final AddResourceResponse addResourceResponse = resourceService.addResource(multipartFile, addResourcePayload);

        Assertions.assertNotNull(addResourceResponse);
        Assertions.assertTrue(addResourceResponse.success());

    }


    @Test
    void shouldThrowException_whenFileIsNull() {

        final Library fakeLibrary = new Library(1L, "name", "address", new ArrayList<>());
        final Librarian fakeLibrarian = new Librarian();
        fakeLibrarian.setLibrary(fakeLibrary);

        final AddResourceResponse addResourceResponse = resourceService.addResource(null, addResourcePayload);
        Assertions.assertFalse(addResourceResponse.success());

        Assertions.assertEquals("File must not be null", addResourceResponse.getMessage());
    }


    @Test
    void shouldThrowException_whenLibraryNotFound() {

        final Librarian fakeLibrarian = new Librarian();
        final AddResourceResponse addResourceResponse = resourceService.addResource(null, addResourcePayload);
        Assertions.assertFalse(addResourceResponse.success());

    }


}
