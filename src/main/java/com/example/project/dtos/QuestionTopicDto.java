package com.example.project.dtos;

import com.example.project.entities.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter

public class QuestionTopicDto {
    private Long id;
    private ServiceType serviceType;
    private String title;
    private boolean active;
    private List<QuestionDto> questions;
}
