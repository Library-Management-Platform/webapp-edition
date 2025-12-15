package com.platform.libraryManager.payloads.library;

import java.time.LocalDateTime;

public class EditLibraryPayload {

    private String name;
    private String address;

    public EditLibraryPayload() {}

    public EditLibraryPayload(
            String name,
            String address
    ) {
        setName(name);
        setAddress(address);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

}
