package com.platform.libraryManager.utils.factories;

import com.platform.libraryManager.utils.helpers.DateHelper;
import com.platform.libraryManager.dataAccess.models.Admin;
import com.platform.libraryManager.dto.payloads.admin.AddAdminPayload;
import com.platform.libraryManager.dto.payloads.admin.EditAdminPayload;

public abstract class AdminFactory {

    public static Admin createEmpty() { return new Admin(); }

    public static Admin create(AddAdminPayload addAdminPayload, Admin parent) {

        return new Admin(
                addAdminPayload.getUsername(),
                addAdminPayload.getEmail(),
                addAdminPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime(),
                parent
        );
    }


    public static Admin create(Long id, EditAdminPayload editAdminPayload) {

        return new Admin(
                id,
                editAdminPayload.getUsername(),
                editAdminPayload.getEmail(),
                editAdminPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime()
        );
    }
}
