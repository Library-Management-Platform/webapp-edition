package com.platform.libraryManager.ui.controllers.admin;


import com.platform.libraryManager.utils.helpers.RedirectHelper;
import com.platform.libraryManager.utils.helpers.RouteAttributeHelper;
import com.platform.libraryManager.dto.payloads.librarian.AddLibrarianPayload;
import com.platform.libraryManager.dto.payloads.librarian.EditLibrarianPayload;
import com.platform.libraryManager.dto.responses.endpoints.librarian.add.AddLibrarianResponse;
import com.platform.libraryManager.dto.responses.endpoints.librarian.edit.EditLibrarianResponse;
import com.platform.libraryManager.dto.responses.endpoints.librarian.getAll.GetAllLibrariansResponse;
import com.platform.libraryManager.dto.responses.endpoints.librarian.getUnique.GetUniqueLibrarianResponse;
import com.platform.libraryManager.dto.searchQueryParams.LibrarianSearchQueryParams;
import com.platform.libraryManager.logic.services.LibrarianService;
import com.platform.libraryManager.logic.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/manage-librarians")
public class AdminLibrarianManagementController {

    @Autowired private LibrarianService librarianService;
    @Autowired private LibraryService libraryService;


    // ----------- Template loading ----------

    @GetMapping()
    public String getLibrarians(Model model) {
        final GetAllLibrariansResponse getAllLibrariansResponse = librarianService.getAllLibrarians();

        RouteAttributeHelper.addModelAttributes(
                model,
                Map.of(
                        "title", "Librarians",
                        "fields", List.of("ID", "Username", "Email", "Library"),

                        "rows", getAllLibrariansResponse.getLibrarians().stream()
                                .map(librarian -> Map.of(
                                        "ID", librarian.getId(),
                                        "Username", librarian.getUsername(),
                                        "Email", librarian.getEmail(),
                                        "Library", librarian.getLibrary().getName()
                                )).toList()

                )
        );

        return "admin/pages/manage-librarians/librarian-list";
    }


    @GetMapping("/add")
    public String addLibrarianForm(Model model) {
        RouteAttributeHelper.addModelAttributes(
                model,
                Map.of(
                        "addLibrarianPayload", new AddLibrarianPayload(),
                        "libraries", libraryService.getAllLibraries().getLibraries()
                )

        );
        return "admin/pages/manage-librarians/add-librarian";
    }

    @GetMapping("/edit/{id}")
    public String editLibrarianForm(
            @PathVariable("id") Long id,
            Model model
    ) {
        final GetUniqueLibrarianResponse getUniqueLibrarianResponse = librarianService.getUniqueLibrarian(new LibrarianSearchQueryParams(id, null, null, null, null, null));

        if(getUniqueLibrarianResponse.success()) {

            RouteAttributeHelper.addModelAttributes(
                    model,
                    Map.of(
                            "id", id,
                            "libraries", libraryService.getAllLibraries().getLibraries(),

                            "editLibrarianPayload", new EditLibrarianPayload(
                                    getUniqueLibrarianResponse.getLibrarian().getUsername(),
                                    getUniqueLibrarianResponse.getLibrarian().getEmail(),
                                    getUniqueLibrarianResponse.getLibrarian().getPassword(),
                                    getUniqueLibrarianResponse.getLibrarian().getLibrary().getId()
                            )
                    )
            );
        }

        return "admin/pages/manage-librarians/edit-librarian";
    }




    // ----------- CRUD Opearions ----------

    @PostMapping("/add")
    public String addLibrarian(
            Model model,
            AddLibrarianPayload addLibrarianPayload
    ) {
        final AddLibrarianResponse addLibrarianResponse = librarianService.addLibrarian(addLibrarianPayload);

        return switch(addLibrarianResponse.getCode()) {

            case 201 -> "redirect:/admin/manage-librarians";

            default -> {
                model.addAttribute("message", addLibrarianResponse.getMessage());
                yield "admin/pages/manage-librarians/add-librarian";
            }
        };

    }


    @PatchMapping("/edit/{id}")
    public String editLibrarian(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            EditLibrarianPayload editLibrarianPayload
    ) {

        final EditLibrarianResponse editLibrarianResponse = librarianService.editLibrarian(id, editLibrarianPayload);

        return switch(editLibrarianResponse.getCode()) {

            case 200 -> "redirect:/admin/manage-librarians";

            default -> RedirectHelper.addFlashAttributesAndRedirect(
                    redirectAttributes,
                    Map.of("message", editLibrarianResponse.getMessage()), "redirect:/admin/manage-librarians/edit/{id}"
            );

        };
    }


    @DeleteMapping("/remove/{id}")
    public String removeLibrarian(@PathVariable("id") Long id) {
        librarianService.removeLibrarian(id);
        return "redirect:/admin/manage-librarians";
    }

}
