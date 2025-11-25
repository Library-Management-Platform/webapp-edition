package com.platform.libraryManager.configurations;


import com.platform.libraryManager.configurationProperties.JWTConfigurationProperties;
import com.platform.libraryManager.providers.JWTProvider;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JWTConfiguration {

    @Autowired private JWTConfigurationProperties jwtConfigurationProperties;


    @Bean
    public JWTProvider jwtProvider() {

        return new JWTProvider(
                Keys.hmacShaKeyFor(jwtConfigurationProperties.getSecret().getBytes()),
                jwtConfigurationProperties.getExpirationMs(),
                jwtConfigurationProperties.getIssuer()
        );
    }

}
