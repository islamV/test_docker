package com.example.project.question;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionUpdateRequest {

    private Long questionTopicId;

    @Size(max = 500)
    private String text;

}

