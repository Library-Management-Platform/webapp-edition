package com.platform.libraryManager.payloads.librarian;

import com.platform.libraryManager.models.Library;

public class EditLibrarianPayload {

    private String username;
    private String email;
    private String password;
    private Long libraryId;

    public EditLibrarianPayload() {}

    public EditLibrarianPayload(
            String username,
            String email,
            String password,
            Long libraryId
    ){
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setLibraryId(libraryId);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getLibraryId() { return libraryId; }
    public void setLibraryId(Long libraryId) { this.libraryId = libraryId; }
}
