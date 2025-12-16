package com.platform.libraryManager.dto.responses.endpoints.admin.getAll;

import com.platform.libraryManager.dataAccess.models.Admin;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

import java.util.ArrayList;
import java.util.List;

public abstract class GetAllAdminsResponse extends Response {

    private List<Admin> admins;

    public GetAllAdminsResponse(
            int code,
            String message,
            ResponseType type
    ) {
        super(code, message, type);
        setAdmins(new ArrayList<>());
    }

    public List<Admin> getAdmins() { return admins; }
    public void setAdmins(List<Admin> admins) { this.admins = admins; }
}
