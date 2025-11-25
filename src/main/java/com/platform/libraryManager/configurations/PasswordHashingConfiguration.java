package com.platform.libraryManager.configurations;


import com.platform.libraryManager.configurationProperties.PasswordHashingConfigurationProperties;
import com.platform.libraryManager.providers.PasswordHashingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class PasswordHashingConfiguration {

    @Autowired private PasswordHashingConfigurationProperties passwordHashingConfigurationProperties;


    @Bean
    public PasswordHashingProvider passwordEncoder() {
        return new PasswordHashingProvider(
                new Argon2PasswordEncoder(
                        passwordHashingConfigurationProperties.getSaltLength(),
                        passwordHashingConfigurationProperties.getHashLength(),
                        passwordHashingConfigurationProperties.getParallelism(),
                        passwordHashingConfigurationProperties.getMemory(),
                        passwordHashingConfigurationProperties.getIterations()
                )
        );
    }
}
