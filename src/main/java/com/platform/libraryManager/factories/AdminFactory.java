package com.platform.libraryManager.factories;

import com.platform.libraryManager.helpers.DateHelper;
import com.platform.libraryManager.models.Admin;
import com.platform.libraryManager.payloads.admin.AddAdminPayload;
import com.platform.libraryManager.payloads.admin.EditAdminPayload;

public abstract class AdminFactory {

    public static Admin createEmpty() { return new Admin(); }

    public static Admin create(AddAdminPayload addAdminPayload) {

        return new Admin(
                addAdminPayload.getUsername(),
                addAdminPayload.getEmail(),
                addAdminPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime()
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
