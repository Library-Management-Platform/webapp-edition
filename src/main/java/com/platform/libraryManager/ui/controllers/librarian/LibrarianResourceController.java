package com.platform.libraryManager.ui.controllers.librarian;


import com.platform.libraryManager.utils.helpers.RedirectHelper;
import com.platform.libraryManager.utils.helpers.RouteAttributeHelper;
import com.platform.libraryManager.dto.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.dto.payloads.resource.EditResourcePayload;
import com.platform.libraryManager.dto.responses.endpoints.resource.add.AddResourceResponse;
import com.platform.libraryManager.dto.responses.endpoints.resource.edit.EditResourceResponse;
import com.platform.libraryManager.dto.responses.endpoints.resource.getUnique.GetUniqueResourceResponse;
import com.platform.libraryManager.dto.searchQueryParams.ResourceSearchQueryParams;
import com.platform.libraryManager.logic.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/librarian/manage-resources")
public class LibrarianResourceController {


    @Autowired private ResourceService resourceService;


    // ----------- Template loading ----------

    @GetMapping()
    public String getResources(Model model) {
        return "/librarian/pages/manage-resources/resources-list";
    }


    @GetMapping("/add")
    public String addResourceForm(Model model) {
        model.addAttribute("addResourcePayload", new AddResourcePayload());
        return "librarian/pages/manage-resources/add-resource";
    }


    @GetMapping("/edit/{id}")
    public String editResourceForm(
            @PathVariable("id") Long id,
            Model model
    ){
        final GetUniqueResourceResponse getUniqueResourceResponse = resourceService.getUniqueResource(new ResourceSearchQueryParams(id, null, null, null, null, null, null, null, null));

        if(getUniqueResourceResponse.success()) {

            RouteAttributeHelper.addModelAttributes(
                    model,
                    Map.of(
                            "id", id,

                            "editResourcePayload", new EditResourcePayload(
                                    getUniqueResourceResponse.getResource().getTitle(),
                                    getUniqueResourceResponse.getResource().getAuthor(),
                                    getUniqueResourceResponse.getResource().getCategory(),
                                    getUniqueResourceResponse.getResource().getType(),
                                    getUniqueResourceResponse.getResource().getLibrary().getId()
                            )
                    )
            );
        }

        return "librarian/pages/manage-resources/edit-resource";
    }




    // ----------- CRUD operations ----------

    @PostMapping("/add")
    public String addResource(
            Model model,
            AddResourcePayload addResourcePayload,
            @RequestParam("file") MultipartFile file
    ) {
        final AddResourceResponse addResourceResponse = resourceService.addResource(file, addResourcePayload);


        return switch(addResourceResponse.getCode()) {

            case 201 -> "redirect:/librarian/dashboard";

            default -> {
                model.addAttribute("message", addResourceResponse.getMessage());
                yield "librarian/pages/manage-resources/add-resource";
            }
        };
    }


    @PatchMapping("/edit/{id}")
    public String editResource(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            EditResourcePayload editResourcePayload
    ) {
        final EditResourceResponse editResourceResponse = resourceService.editResource(id, editResourcePayload);

        return switch(editResourceResponse.getCode()) {

            case 200 -> "redirect:/librarian/dashboard";

            default -> RedirectHelper.addFlashAttributesAndRedirect(
                    redirectAttributes,
                    Map.of("message", editResourceResponse.getMessage()), "redirect:/librarian/manage-resources/edit/{id}"
            );

        };
    }


    @DeleteMapping("/remove/{id}")
    public String removeResource(@PathVariable("id") Long id) {
        resourceService.removeResource(id);
        return "redirect:/librarian/dashboard";
    }




}
