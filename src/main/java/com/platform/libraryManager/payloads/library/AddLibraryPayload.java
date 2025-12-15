package com.platform.libraryManager.payloads.library;

import java.time.LocalDateTime;

public class AddLibraryPayload {

    private String name;
    private String address;

    public AddLibraryPayload() {}

    public AddLibraryPayload(
            String name,
            String address,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        setName(name);
        setAddress(address);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }


}
