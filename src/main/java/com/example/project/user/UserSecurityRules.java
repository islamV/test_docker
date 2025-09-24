package com.example.project.user;

import com.example.project.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityRules implements SecurityRules {

    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {

        auth.requestMatchers(HttpMethod.GET, "/users").hasAnyRole("ADMIN", "MANAGER");
        auth.requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "MANAGER");


        auth.requestMatchers(HttpMethod.POST,   "/users").hasRole("ADMIN");
        auth.requestMatchers(HttpMethod.PATCH,  "/users/**").hasRole("ADMIN");
        auth.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN");
    }
}
