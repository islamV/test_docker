package com.example.project.chats;

import com.example.project.common.DuplicateResourceException;

public class DuplicateChatException extends DuplicateResourceException {
    public DuplicateChatException(String message) {
        super(message);
    }
}
