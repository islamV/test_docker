package com.example.project.reports;

import com.example.project.entities.ServiceType;
import com.fasterxml.jackson.databind.JsonNode;

public record CreateReportRequest(
        ServiceType serviceType,
        long serviceId,
        JsonNode data
) { }