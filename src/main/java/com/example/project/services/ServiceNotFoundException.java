package com.example.project.services;

import com.example.project.common.ResourceNotFoundException;

public class ServiceNotFoundException  extends ResourceNotFoundException {
    public ServiceNotFoundException(Long id) {
        super("Service with id " + id + " not found");
    }
}
