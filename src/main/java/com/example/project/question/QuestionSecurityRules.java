package com.example.project.question;

import com.example.project.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class QuestionSecurityRules implements SecurityRules {

        @Override
        public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry r) {

            r.requestMatchers(HttpMethod.GET,    "/questions", "/questions/*")
                    .hasAnyRole("ADMIN", "OFFICER");

            r.requestMatchers(HttpMethod.POST,   "/questions")
                    .hasRole("ADMIN");

            r.requestMatchers(HttpMethod.PATCH,  "/questions/*")
                    .hasRole("ADMIN");

            r.requestMatchers(HttpMethod.DELETE, "/questions/*")
                    .hasRole("ADMIN");

            r.requestMatchers(HttpMethod.GET, "/question-bank")
                    .hasAnyRole("ADMIN", "OFFICER");

            r.requestMatchers(HttpMethod.GET, "/question-topic")
                    .hasAnyRole("ADMIN", "OFFICER");
        }
}
