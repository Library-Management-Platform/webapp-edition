package com.platform.libraryManager.setup.configurationProperties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail.smtp")
public class SMTPConfigurationProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private boolean auth;
    private boolean starttlsEnable;
    private boolean starttlsRequired;

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getUsername() {return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAuth() { return auth; }
    public void setAuth(boolean auth) { this.auth = auth; }

    public boolean isStarttlsEnable() { return starttlsEnable; }
    public void setStarttlsEnable(boolean starttlsEnable) { this.starttlsEnable = starttlsEnable; }

    public boolean isStarttlsRequired() { return starttlsRequired; }
    public void setStarttlsRequired(boolean starttlsRequired) { this.starttlsRequired = starttlsRequired; }
}
