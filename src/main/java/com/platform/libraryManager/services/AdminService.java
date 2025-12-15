package com.platform.libraryManager.services;


import com.platform.libraryManager.factories.AdminFactory;
import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.payloads.admin.AddAdminPayload;
import com.platform.libraryManager.payloads.admin.EditAdminPayload;
import com.platform.libraryManager.repositories.AdminRepository;
import com.platform.libraryManager.responses.endpoints.admin.add.AddAdminErrorResponse;
import com.platform.libraryManager.responses.endpoints.admin.add.AddAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.add.AddAdminSuccessResponse;
import com.platform.libraryManager.responses.endpoints.admin.edit.EditAdminErrorResponse;
import com.platform.libraryManager.responses.endpoints.admin.edit.EditAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.edit.EditAdminSuccessResponse;
import com.platform.libraryManager.responses.endpoints.admin.getAll.GetAllAdminsResponse;
import com.platform.libraryManager.responses.endpoints.admin.getAll.GetAllAdminsSuccessResponse;
import com.platform.libraryManager.responses.endpoints.admin.getUnique.GetUniqueAdminErrorResponse;
import com.platform.libraryManager.responses.endpoints.admin.getUnique.GetUniqueAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.getUnique.GetUniqueAdminSuccessResponse;
import com.platform.libraryManager.responses.endpoints.admin.remove.RemoveAdminErrorResponse;
import com.platform.libraryManager.responses.endpoints.admin.remove.RemoveAdminResponse;
import com.platform.libraryManager.responses.endpoints.admin.remove.RemoveAdminSuccessResponse;
import com.platform.libraryManager.searchQueryParams.AdminSearchQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired private AdminRepository adminRepository;

    public AddAdminResponse addAdmin(AddAdminPayload addAdminPayload) {

        try {

            final Admin admin = AdminFactory.create(addAdminPayload);
            adminRepository.save(admin);
            return new AddAdminSuccessResponse();

        }catch(DataIntegrityViolationException dataIntegrityViolationException) {
            return new AddAdminErrorResponse(409, "Admin already exists");

        } catch(Exception exception) {
            return new AddAdminErrorResponse(400, "Error");

        }
    }

    public EditAdminResponse editAdmin(Long id, EditAdminPayload editAdminPayload) {

        try {

            final Admin admin = AdminFactory.create(id, editAdminPayload);
            adminRepository.save(admin);
            return new EditAdminSuccessResponse();

        }catch(DataIntegrityViolationException dataIntegrityViolationException) {
            return new EditAdminErrorResponse(409, "An Admin with the same username already exists");

        } catch(Exception exception) {
            return new EditAdminErrorResponse(400, "Error");

        }
    }

    public RemoveAdminResponse removeAdmin(Long id) {

        try {
            final Admin admin = getUniqueAdmin(new AdminSearchQueryParams(id, null, null, null, null, null)).getAdmin();
            adminRepository.delete(admin);
            return new RemoveAdminSuccessResponse();

        }catch (Exception exception) {
            return new RemoveAdminErrorResponse(400, "Error");
        }
    }




    public GetAllAdminsResponse getAllAdmins() {
        final List<Admin> admins = adminRepository.findAll();
        return new GetAllAdminsSuccessResponse(admins);
    }

    public GetUniqueAdminResponse getUniqueAdmin(AdminSearchQueryParams adminSearchQueryParams) {

        try {
            final Admin admin = adminRepository.searchOne(adminSearchQueryParams).get();
            return new GetUniqueAdminSuccessResponse(admin);

        }catch (Exception exception) {
            return new GetUniqueAdminErrorResponse();
        }
    }
}
