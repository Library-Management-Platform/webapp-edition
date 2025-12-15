package com.platform.libraryManager.controllers.librarian;


import com.platform.libraryManager.payloads.resource.AddResourcePayload;
import com.platform.libraryManager.responses.endpoints.resource.add.AddResourceResponse;
import com.platform.libraryManager.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String editLibrarianForm(
            @PathVariable("id") Long id,
            org.springframework.ui.Model model
    ){
        return "";
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






}
