package com.example.project.questionTopic;

import com.example.project.entities.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionTopicRequest
{
    @NotBlank
    @Size(max = 200)
    private String title;

    @NotNull
    private ServiceType serviceType;


    private Boolean active = true;
}


