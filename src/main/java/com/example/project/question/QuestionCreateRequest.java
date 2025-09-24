package com.example.project.question;



import com.example.project.entities.AnswerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionCreateRequest {

    @NotNull
    private Long questionTopicId;

    @NotBlank
    @Size(max = 500)
    private String text;


}
