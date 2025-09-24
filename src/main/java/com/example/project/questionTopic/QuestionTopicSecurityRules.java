package com.example.project.questionTopic;


import com.example.project.common.SecurityRules;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class QuestionTopicSecurityRules implements SecurityRules {

    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {


        registry.requestMatchers(HttpMethod.GET, "/question-topics").hasAnyRole("ADMIN", "OFFICER");
        registry.requestMatchers(HttpMethod.GET, "/question-topics/**").hasAnyRole("ADMIN", "OFFICER");


        registry.requestMatchers(HttpMethod.POST,   "/question-topics").hasRole("ADMIN");
        registry.requestMatchers(HttpMethod.PATCH,  "/question-topics/**").hasRole("ADMIN");
        registry.requestMatchers(HttpMethod.DELETE, "/question-topics/**").hasRole("ADMIN");
    }
}