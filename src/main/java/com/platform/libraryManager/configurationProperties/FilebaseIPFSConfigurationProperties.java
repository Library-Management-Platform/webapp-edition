package com.platform.libraryManager.configurationProperties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "filebase")
public class FilebaseIPFSConfigurationProperties {

    private String accessKey;
    private String secretKey;
    private String bucket;
    private String endpoint;
    private String region;
    private String ipfsGateway; // optional, for constructing IPFS URLs


    public String getAccessKey() { return accessKey; }
    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getBucket() { return bucket; }
    public void setBucket(String bucket) { this.bucket = bucket; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getIpfsGateway() { return ipfsGateway; }
    public void setIpfsGateway(String ipfsGateway) { this.ipfsGateway = ipfsGateway; }

}
