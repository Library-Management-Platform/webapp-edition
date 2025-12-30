package com.platform.libraryManager.ui;


import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dataAccess.repositories.LibrarianRepository;
import com.platform.libraryManager.dataAccess.repositories.LibraryRepository;
import com.platform.libraryManager.shared.enums.ResourceCategoryEnum;
import com.platform.libraryManager.shared.enums.ResourceTypeEnum;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LibrarianResourceControllerIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired LibraryRepository libraryRepository;
    @Autowired LibrarianRepository librarianRepository;

    private Long libraryId;


    @BeforeEach
    void setup() {
        final Library library = new Library();
        library.setName("Test Library");

        final Librarian librarian = new Librarian(
                "librarian",
                "email",
                "password",
                null,
                null,
                library
        );

        libraryId = libraryRepository.save(library).getId();
        librarianRepository.save(librarian);
    }


    @Test
    void shouldNotRenderResourceListPageIfNotAuthenticated() throws Exception{
        mockMvc.perform(get("/librarian/manage-resources")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "librarian", authorities = {"LIBRARIAN"})
    void shouldNotRenderResourceListPageIfAuthenticated() throws Exception{
        mockMvc.perform(get("/librarian/manage-resources"))
                .andExpect(status().isOk())
                .andExpect(view().name("/librarian/pages/manage-resources/resources-list"));
    }


    @Test
    @WithMockUser(username = "librarian", authorities = {"LIBRARIAN"})
    void shouldAddResourceAndRedirect() throws Exception {

        mockMvc.perform(
                multipart("/librarian/manage-resources/add")
                        .file(new MockMultipartFile(
                                "file",                      // name of the request param
                                "test.pdf",                  // original file name
                                "application/pdf",           // content type
                                "dummy content".getBytes()   // file content
                        ))

                        .param("title", "title")
                        .param("author", "author")
                        .param("category", ResourceCategoryEnum.HISTORY.name())
                        .param("type", ResourceTypeEnum.BOOK.name())
                        .param("libraryId", libraryId.toString())

                        .with(csrf())
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/librarian/dashboard"));

    }
}
