package com.example.project.questionTopic;

import com.example.project.common.DuplicateResourceException;

public class DuplicateTopicException extends DuplicateResourceException {
    public DuplicateTopicException() {
        super("This Topic is already exist");
    }
}
