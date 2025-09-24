package com.example.project.dtos;

import com.example.project.entities.ServiceType;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;

public record ReportDto(
        Long id,
        String serviceName,
        Instant createdAt,
        String createdByName

) { }

