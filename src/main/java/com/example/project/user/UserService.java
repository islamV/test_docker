package com.example.project.user;

import com.example.project.dtos.UserDto;
import com.example.project.mappers.UserMapper;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    // GET /users
    public List<UserDto> list() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    // GET /users/{id}
    public UserDto getById(Long id) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toDto(entity);
    }

    // POST /users
    public UserDto create(CreateUserRequest request) {
        var normalizedEmail = normalizeEmail(request.getEmail());

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateUserException(normalizedEmail);
        }

        var entity = mapper.toEntity(request);
        entity.setEmail(normalizedEmail);

        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity = userRepository.save(entity);
        return mapper.toDto(entity);
    }

    // PATCH /users/{id}
    public UserDto update(Long id, UpdateUserRequest request) {
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        mapper.update(request, entity); // MapStruct: يتجاهل null
        entity = userRepository.save(entity);
        return mapper.toDto(entity);
    }

    // DELETE /users/{id}
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    // PUT /users/{id}/password
    public void changePassword(Long userId, ChangePasswordRequest request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new AccessDeniedException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        var normalized = email.trim().toLowerCase(Locale.ROOT);
        if (normalized.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        return normalized;
    }

}
