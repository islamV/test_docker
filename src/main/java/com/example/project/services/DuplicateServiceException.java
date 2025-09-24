package com.example.project.services;

import com.example.project.common.DuplicateResourceException;

public class DuplicateServiceException extends DuplicateResourceException {
    public DuplicateServiceException( String message ){
        super(message);

    }
}
