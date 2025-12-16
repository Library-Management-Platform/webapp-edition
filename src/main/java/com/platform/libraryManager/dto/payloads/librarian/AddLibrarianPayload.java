package com.platform.libraryManager.dto.payloads.librarian;

public class AddLibrarianPayload {

    private String username;
    private String email;
    private String password;
    private Long libraryId;

    public AddLibrarianPayload() {}

    public AddLibrarianPayload(
            String username,
            String email,
            Long libraryId
    ){
        setUsername(username);
        setEmail(email);
        setPassword(username);
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
