package com.example.project.questionTopic;

import com.example.project.common.ResourceNotFoundException;

public class TopicNotFoundException extends ResourceNotFoundException {
    public TopicNotFoundException(Long id) {
        super("Topic " + id + " not found");
    }
}
