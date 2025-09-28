package com.example.project.bootstrap;

import com.example.project.entities.Role;
import com.example.project.entities.User;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DefaultAdminInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DefaultAdminInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        var normalizedEmail = "admin@admin.com".toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmail(normalizedEmail)) {
            return;
        }

        var admin = User.builder()
                .email(normalizedEmail)
                .fullName("Admin")
                .password(passwordEncoder.encode("123456789"))
                .role(Role.ADMIN)
                .active(true)
                .build();

        userRepository.save(admin);
        log.info("Default admin user created with email {}", normalizedEmail);
    }
}
