package com.platform.libraryManager.setup.configurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "radar.geolocation")
public class RadarGeolocationConfigurationProperties {

    private String secretKey;
    private String publishKey;
    private String testSecretKey;
    private String testPublishKey;


    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getPublishKey() { return publishKey; }
    public void setPublishKey(String publishKey) { this.publishKey = publishKey; }

    public String getTestSecretKey() { return testSecretKey; }
    public void setTestSecretKey(String testSecretKey) { this.testSecretKey = testSecretKey; }

    public String getTestPublishKey() { return testPublishKey; }
    public void setTestPublishKey(String testPublishKey) { this.testPublishKey = testPublishKey; }


}
