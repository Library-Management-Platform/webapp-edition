package com.platform.libraryManager.controllers.admin;

import com.platform.libraryManager.helpers.RouteAttributeHelper;
import com.platform.libraryManager.responses.endpoints.library.getAll.GetAllLibrariesResponse;
import com.platform.libraryManager.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "admin/libraries";
    }


    @DeleteMapping("/{id}")
    public String removeLibrary(@PathVariable("id") Long id) {
        libraryService.removeLibrary(id);
        return "redirect:/admin/libraries";
    }


}
