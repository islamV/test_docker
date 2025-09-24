package com.example.project.notification;

import com.example.project.common.ResourceNotFoundException;

public class NotificationNotFoundException extends ResourceNotFoundException {
    public NotificationNotFoundException(Long id) { super("Notification with id " + id + " not found"); }

}
