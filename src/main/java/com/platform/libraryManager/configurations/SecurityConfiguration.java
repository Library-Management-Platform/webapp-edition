package com.platform.libraryManager.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http
                .csrf(csrf -> csrf.disable()) // disable CSRF in Spring Security 7
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all requests
                );
        return http.build();*/


        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/sign-up").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
