package com.platform.libraryManager.payloads.authPayloads;

public class LoginAuthPayload {

    private String username;
    private String password;

    public LoginAuthPayload() {}

    public LoginAuthPayload(
            String username,
            String password
    ){
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
