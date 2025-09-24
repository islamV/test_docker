package com.example.project.question;

import com.example.project.common.ResourceNotFoundException;

public class QuestionNotFoundException extends ResourceNotFoundException {
    public QuestionNotFoundException(Long id) {
        super("Question " + id + " not found");
    }}