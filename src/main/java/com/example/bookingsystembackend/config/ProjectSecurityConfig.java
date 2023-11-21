package com.example.bookingsystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    // Configures the default security filter chain for the application.
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Disables Cross-Site Request Forgery (CSRF) protection for simplicity in this example.
        http.csrf((csrf) -> csrf.disable())
                // Configures URL-based security by specifying which requests should be authenticated or permitted.
                .authorizeHttpRequests((requests) -> requests
                        // Configures authentication for specific URLs ("/myprofile" and "/mybookings").
                        .requestMatchers("/myprofile", "/mybookings").authenticated()
                        // Configures permitAll for specific URLs ("/contact" and "/login").
                        .requestMatchers("/contact", "/login").permitAll())
                // Configures form-based login with default settings.
                .formLogin(Customizer.withDefaults())
                // Configures HTTP Basic authentication with default settings.
                .httpBasic(Customizer.withDefaults());

        // Builds and returns the configured security filter chain.
        return http.build();
    }

    // Defines a PasswordEncoder bean for encoding and verifying passwords.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Uses BCryptPasswordEncoder as the password encoder.
        return new BCryptPasswordEncoder();
    }
}
