package com.example.project.notification;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateNotificationRequest(
        @NotNull Long userId,
        @NotNull Long reportId,
        @NotBlank @Size(max = 200) String title,
        @NotBlank @Size(max = 500) String body,
        JsonNode data
) {}
