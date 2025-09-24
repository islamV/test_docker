package com.example.project.dtos;

import com.example.project.entities.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class ServiceDto {
    private Long id;
    private ServiceType serviceType;
    private String name;
    private String photoUrl;
    private Long createdById;
}
