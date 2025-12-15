package com.platform.libraryManager.payloads.admin;

public class AddAdminPayload {

    private String username;
    private String email;
    private String password;

    public AddAdminPayload() { setPassword("admin"); }

    public AddAdminPayload(
            String username,
            String email
    ){
        setUsername(username);
        setEmail(email);
        setPassword("admin");
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
