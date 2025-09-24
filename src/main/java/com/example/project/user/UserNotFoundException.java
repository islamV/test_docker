package com.example.project.user;

import com.example.project.common.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(Long id) {
        super("User with id " + id + " not found");
    }
}
