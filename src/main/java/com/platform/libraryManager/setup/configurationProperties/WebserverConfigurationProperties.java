package com.platform.libraryManager.setup.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class WebserverConfigurationProperties {

    private String host;
    private String protocol;
    private int port;

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }


    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }


    public String getFullAddress() { return protocol + "://" + host + ":" + port; }



}
