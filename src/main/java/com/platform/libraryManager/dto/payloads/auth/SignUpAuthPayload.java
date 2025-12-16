package com.platform.libraryManager.dto.payloads.auth;

public class SignUpAuthPayload {

    private String username;
    private String email;
    private String password;

    public SignUpAuthPayload() {}

    public SignUpAuthPayload(
            String username,
            String email,
            String password
    ){
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
