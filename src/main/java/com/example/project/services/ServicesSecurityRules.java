package com.example.project.services;

import com.example.project.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ServicesSecurityRules implements SecurityRules {

    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry Registry) {


        Registry.requestMatchers(HttpMethod.GET, "/services").hasAnyRole("ADMIN", "OFFICER");
        Registry.requestMatchers(HttpMethod.GET, "/services/**").hasAnyRole("ADMIN", "OFFICER");


        Registry.requestMatchers(HttpMethod.POST,   "/services").hasRole("ADMIN");
        Registry.requestMatchers(HttpMethod.PATCH,  "/services/**").hasRole("ADMIN");
        Registry.requestMatchers(HttpMethod.DELETE, "/services/**").hasRole("ADMIN");
    }
}