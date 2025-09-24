package com.example.project.reports;

import com.example.project.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
    public class ReportsSecurityRules implements SecurityRules {

        @Override
        public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {


            auth.requestMatchers(HttpMethod.GET, "/reports").hasAnyRole("ADMIN", "OFFICER");
            auth.requestMatchers(HttpMethod.GET, "/reports/**").hasAnyRole("ADMIN", "OFFICER");


            auth.requestMatchers(HttpMethod.POST, "/reports").hasAnyRole("ADMIN", "OFFICER");


            auth.requestMatchers(HttpMethod.DELETE, "/reports/**").hasRole("ADMIN");
        }
    }

