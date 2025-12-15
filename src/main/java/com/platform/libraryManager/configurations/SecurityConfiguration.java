package com.platform.libraryManager.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;


@Configuration
public class SecurityConfiguration {


    @Bean public HiddenHttpMethodFilter hiddenHttpMethodFilter() { return new HiddenHttpMethodFilter(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/sign-up", "/email-verification/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
