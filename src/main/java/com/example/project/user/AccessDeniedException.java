package com.example.project.user;

public class AccessDeniedException extends com.example.project.common.AccessDeniedException {
    public AccessDeniedException() {
        super("Access denied");
    }
}
