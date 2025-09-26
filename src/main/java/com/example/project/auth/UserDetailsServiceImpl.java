package com.example.project.auth;

import com.example.project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var normalizedEmail = normalizeEmail(email);

        var user = userRepository.findByEmail(normalizedEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            throw new UsernameNotFoundException("User not found");
        }
        var normalized = email.trim().toLowerCase(Locale.ROOT);
        if (normalized.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return normalized;
    }


}
