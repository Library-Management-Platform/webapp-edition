package com.platform.libraryManager.dto.responses.endpoints.admin.getUnique;

import com.platform.libraryManager.dataAccess.models.Admin;
import com.platform.libraryManager.dto.responses.Response;
import com.platform.libraryManager.dto.responses.types.ResponseType;

public abstract class GetUniqueAdminResponse extends Response {

    private Admin admin;

    public GetUniqueAdminResponse(int code, String message, ResponseType type) {
        super(code, message, type);
    }

    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }

}
