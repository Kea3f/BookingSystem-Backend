package com.example.bookingsystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class OAuth2Config {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(googleClientRegistration());
    }

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE
                .getBuilder("google")
                .clientId("709858909803-epki5mbcq0d30asms5uuenrq2hddk3f6.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("{baseUrl}/login/oauth2/code/google")
                .scope("openid", "profile", "email", "https://www.googleapis.com/auth/calendar")
                .build();
    }


}
