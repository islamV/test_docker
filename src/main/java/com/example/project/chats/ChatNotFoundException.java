package com.example.project.chats;

import com.example.project.common.ResourceNotFoundException;

public class ChatNotFoundException extends ResourceNotFoundException {
    public ChatNotFoundException(Long id) {
        super("Chat with id " + id + " not found");
    }
}
