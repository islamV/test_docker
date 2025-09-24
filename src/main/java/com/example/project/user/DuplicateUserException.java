package com.example.project.user;

import com.example.project.common.DuplicateResourceException;

public class DuplicateUserException extends DuplicateResourceException {
    public DuplicateUserException(String email){
        super("this email: "+email+" is already exist ");

    }
}
