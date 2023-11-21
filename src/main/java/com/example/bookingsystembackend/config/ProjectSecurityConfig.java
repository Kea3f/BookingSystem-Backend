package com.example.bookingsystembackend.config;
import com.example.bookingsystembackend.service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Import(SecurityConfig.class)
public class ProjectSecurityConfig{

    private final UserDetailsService userDetailsService;
    public ProjectSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    // Configures the default security filter chain for the application.
    @Bean
   public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Disables Cross-Site Request Forgery (CSRF) protection for simplicity in this example.
        http.csrf(AbstractHttpConfigurer::disable)
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

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return  authenticationConfiguration.getAuthenticationManager();
        }


    }