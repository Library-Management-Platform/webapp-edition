package com.platform.libraryManager.setup.configurations;


import com.platform.libraryManager.setup.configurationProperties.PasswordHashingConfigurationProperties;
import com.platform.libraryManager.shared.providers.PasswordHashingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;


@Configuration
public class PasswordHashingConfiguration {

    @Autowired private PasswordHashingConfigurationProperties passwordHashingConfigurationProperties;


    @Bean
    public PasswordHashingProvider passwordHashingProvider() {
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
