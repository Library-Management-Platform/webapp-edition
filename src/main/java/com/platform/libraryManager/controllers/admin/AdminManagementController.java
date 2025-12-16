package com.platform.libraryManager.controllers.admin;


import com.platform.libraryManager.helpers.RedirectHelper;
import com.platform.libraryManager.helpers.RouteAttributeHelper;
import com.platform.libraryManager.payloads.admin.AddAdminPayload;
import com.platform.libraryManager.payloads.admin.EditAdminPayload;
import com.platform.libraryManager.responses.endpoints.admin.add.AddAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.edit.EditAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.getAll.GetAllAdminsResponse;
import com.platform.libraryManager.responses.endpoints.admin.getUnique.GetUniqueAdminResponse;
import com.platform.libraryManager.searchQueryParams.AdminSearchQueryParams;
import com.platform.libraryManager.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/manage-admins")
public class AdminManagementController {


    @Autowired private AdminService adminService;

    // ----------- Template loading ----------

    @GetMapping()
    public String getAdmins(Model model) {

        //final GetAllAdminsResponse getAllAdminsResponse = adminService.getAllAdmins();
        final GetAllAdminsResponse getAllAdminsResponse = adminService.getDescendantAdmins();

        RouteAttributeHelper.addModelAttributes(
                model,
                Map.of(
                        "title", "Admins",
                        "fields", List.of("ID", "Username", "Email"),

                        "rows", getAllAdminsResponse.getAdmins().stream()
                                .map(admin -> Map.of(
                                        "ID", admin.getId(),
                                        "Username", admin.getUsername(),
                                        "Email", admin.getEmail()
                                )).toList()

                )
        );

        return "admin/pages/manage-admins/admin-list";
    }


    @GetMapping("/add")
    public String addAdminForm(Model model) {
        model.addAttribute("addAdminPayload", new AddAdminPayload());
        return "admin/pages/manage-admins/add-admin";
    }


    @GetMapping("/edit/{id}")
    public String editAdminForm(
            @PathVariable("id") Long id,
            Model model
    ) {
        final GetUniqueAdminResponse getUniqueAdminResponse = adminService.getUniqueAdmin(new AdminSearchQueryParams(id, null, null, null, null, null));

        if(getUniqueAdminResponse.success()) {

            RouteAttributeHelper.addModelAttributes(
                    model,
                    Map.of(
                            "id", id,

                            "editAdminPayload", new EditAdminPayload(
                                    getUniqueAdminResponse.getAdmin().getUsername(),
                                    getUniqueAdminResponse.getAdmin().getEmail(),
                                    getUniqueAdminResponse.getAdmin().getPassword()
                            )
                    )
            );
        }

        return "admin/pages/manage-admins/edit-admin";
    }





    // ----------- CRUD Opearions ----------

    @PostMapping("/add")
    public String addAdmin(
            Model model,
            AddAdminPayload addAdminPayload
    ) {
        final AddAdminResponse addAdminResponse = adminService.addAdmin(addAdminPayload);

        return switch(addAdminResponse.getCode()) {

            case 201 -> "redirect:/admin/manage-admins";

            default -> {
                model.addAttribute("message", addAdminResponse.getMessage());
                yield "admin/pages/manage-admins/add-admin";
            }
        };

    }


    @PatchMapping("/edit/{id}")
    public String editAdmin(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes,
            EditAdminPayload editAdminPayload
    ) {

        final EditAdminResponse editAdminResponse = adminService.editAdmin(id, editAdminPayload);

        return switch(editAdminResponse.getCode()) {

            case 200 -> "redirect:/admin/manage-admins";

            default -> RedirectHelper.addFlashAttributesAndRedirect(
                    redirectAttributes,
                    Map.of("message", editAdminResponse.getMessage()), "redirect:/admin/manage-admins/edit/{id}"
            );

        };
    }


    @DeleteMapping("/remove/{id}")
    public String removeAdmin(@PathVariable("id") Long id) {
        adminService.removeAdmin(id);
        return "redirect:/admin/manage-admins";
    }


}
