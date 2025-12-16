package com.platform.libraryManager.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class SecurityConfiguration {

    @Bean 
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() { 
        return new HiddenHttpMethodFilter(); 
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/ws/**") // WebSocket handshake
            )
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/", "/login", "/sign-up", "/email-verification/**", "/images/**", "/ws/**").permitAll()

                // Swagger endpoints
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/v2/api-docs/**",
                    "/webjars/**"
                ).permitAll()

                // Role-based endpoints
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/client/**").hasAuthority("CLIENT")
                .requestMatchers("/librarian/**").hasAuthority("LIBRARIAN")

                // Any other request requires authentication
                .anyRequest().authenticated()
            )
            .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
