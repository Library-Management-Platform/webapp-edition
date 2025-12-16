package com.platform.libraryManager.setup.configurations;

import com.platform.libraryManager.setup.configurationProperties.FilebaseIPFSConfigurationProperties;
import com.platform.libraryManager.utils.providers.FilebaseIPFSProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class FilebaseIPFSConfiguration {

    @Autowired private FilebaseIPFSConfigurationProperties filebaseIPFSConfigurationProperties;


    @Bean
    public FilebaseIPFSProvider filebaseIPFSProvider() {

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(
                filebaseIPFSConfigurationProperties.getAccessKey(),
                filebaseIPFSConfigurationProperties.getSecretKey()
        );

         return new FilebaseIPFSProvider(
                 S3Client.builder()
                         .region(Region.of(filebaseIPFSConfigurationProperties.getRegion()))
                         .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                         .endpointOverride(URI.create(filebaseIPFSConfigurationProperties.getEndpoint()))
                         .build(),

                 filebaseIPFSConfigurationProperties
         );
    }

}
