package com.platform.libraryManager.providers;

import com.platform.libraryManager.configurationProperties.FilebaseIPFSConfigurationProperties;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.nio.file.Path;


public class FilebaseIPFSProvider {

    private final S3Client s3Client;
    private final FilebaseIPFSConfigurationProperties filebaseIPFSConfigurationProperties;

    public FilebaseIPFSProvider(
            S3Client s3Client,
            FilebaseIPFSConfigurationProperties filebaseIPFSConfigurationProperties
    ) {
        this.s3Client = s3Client;
        this.filebaseIPFSConfigurationProperties = filebaseIPFSConfigurationProperties;
    }


    /**
     * Upload a file to Filebase S3
     *
     * @param key      the object key (file name in S3)
     * @param filePath path to the local file
     * @return the public URL via IPFS gateway
     */
    public String uploadFile(MultipartFile file) throws IOException {

        final String key = file.getOriginalFilename();

        final PutObjectResponse putObjectResponse = s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(filebaseIPFSConfigurationProperties.getBucket())
                        .key(key)
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );

        final String cid = putObjectResponse.sdkHttpResponse().headers().get("x-amz-meta-cid").get(0);

        // Return the IPFS gateway URL
        return filebaseIPFSConfigurationProperties.getIpfsGateway() + "/" + cid;
    }



}
