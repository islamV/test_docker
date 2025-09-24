package com.example.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class QuestionDto {
    private Long id;
    private Long questionTopicId;
    private String text;
}
