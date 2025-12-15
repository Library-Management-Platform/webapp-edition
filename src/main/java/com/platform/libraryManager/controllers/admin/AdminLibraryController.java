package com.platform.libraryManager.controllers.admin;

import com.platform.libraryManager.factories.LibraryFactory;
import com.platform.libraryManager.helpers.RedirectHelper;
import com.platform.libraryManager.helpers.RouteAttributeHelper;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.payloads.library.AddLibraryPayload;
import com.platform.libraryManager.payloads.library.EditLibraryPayload;
import com.platform.libraryManager.responses.endpoints.library.add.AddLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.edit.EditLibraryResponse;
import com.platform.libraryManager.responses.endpoints.library.getAll.GetAllLibrariesResponse;
import com.platform.libraryManager.responses.endpoints.library.getUnique.GetUniqueLibraryResponse;
import com.platform.libraryManager.searchQueryParams.LibrarySearchQueryParams;
import com.platform.libraryManager.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/libraries")
public class AdminLibraryController {

    @Autowired private LibraryService libraryService;

    @GetMapping()
    public String getLibraries(Model model) {

        final GetAllLibrariesResponse getAllLibrariesResponse = libraryService.getAllLibraries();

        RouteAttributeHelper.addModelAttributes(
                model,
                Map.of(
                        "title", "Libraries",
                        "fields", List.of("ID", "Name", "Address"),

                        "rows", getAllLibrariesResponse.getLibraries().stream()
                                .map(library -> Map.of(
                                        "ID", library.getId(),
                                        "Name", library.getName(),
                                        "Address", library.getAddress()
                                )).toList()

                )
        );

        return "admin/libraries/library-list";
    }


    @DeleteMapping("/{id}")
    public String removeLibrary(@PathVariable("id") Long id) {
        libraryService.removeLibrary(id);
        return "redirect:/admin/libraries";
    }









    @GetMapping("/add")
    public String addLibraryForm(Model model) {
        model.addAttribute("addLibraryPayload", new AddLibraryPayload());
        return "admin/libraries/add-library";
    }

    @PostMapping("/add")
    public String addLibrary(
            Model model,
            AddLibraryPayload addLibraryPayload
    ) {
        final AddLibraryResponse addLibraryResponse = libraryService.addLibrary(addLibraryPayload);


        return switch(addLibraryResponse.getCode()) {

            case 201 -> "redirect:/admin/libraries";

            default -> {
                model.addAttribute("message", addLibraryResponse.getMessage());
                yield "admin/libraries/add-library";
            }
        };
    }




    @GetMapping("/edit/{id}")
    public String editLibraryForm(
            @PathVariable("id") Long id,
            Model model
    ) {
        final GetUniqueLibraryResponse getUniqueLibraryResponse = libraryService.getUniqueLibrary(new LibrarySearchQueryParams(id, null, null, null, null));

        if(getUniqueLibraryResponse.success()) {

            RouteAttributeHelper.addModelAttributes(
                    model,
                    Map.of(
                            "id", id,

                            "editLibraryPayload", new EditLibraryPayload(
                                    getUniqueLibraryResponse.getLibrary().getName(),
                                    getUniqueLibraryResponse.getLibrary().getAddress()
                            )
                    )
            );

            return "admin/libraries/edit-library";
        }

        return "redirect:/admin/libraries";
    }



    @PatchMapping("/edit/{id}")
    public String editLibrary(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            EditLibraryPayload editLibraryPayload
    ) {
        final EditLibraryResponse editLibraryResponse = libraryService.editLibrary(id, editLibraryPayload);

        return switch(editLibraryResponse.getCode()) {

            case 200 -> "redirect:/admin/libraries";

            default -> RedirectHelper.addFlashAttributesAndRedirect(
                    redirectAttributes,
                    Map.of("message", editLibraryResponse.getMessage()), "redirect:/admin/libraries/edit/{id}"
            );

        };
    }



}
