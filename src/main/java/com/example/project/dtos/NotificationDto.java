package com.example.project.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter

public class NotificationDto {
    private Long id;
    private Long userId;
    private Long reportId;
    private String title;
    private String body;
    private boolean read;
    private JsonNode data;
    private Instant createdAt;
}
