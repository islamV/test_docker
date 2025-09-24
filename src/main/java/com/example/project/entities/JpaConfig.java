package com.example.project.entities;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {

    @Bean(name = "auditorAware")
    public AuditorAware<Long> auditorAware() {
        return () -> {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated()) return Optional.empty();

            Object p = auth.getPrincipal();
            if (p instanceof Long id) return Optional.of(id);
            if (p instanceof String s) {
                try { return Optional.of(Long.valueOf(s)); } catch (NumberFormatException ignored) {}
            }
            return Optional.empty();
        };
    }
}
