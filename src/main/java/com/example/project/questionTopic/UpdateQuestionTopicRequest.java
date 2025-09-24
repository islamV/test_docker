package com.example.project.questionTopic;


import com.example.project.entities.ServiceType;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionTopicRequest {
        @Size(max = 200)
        private String title;

     //   private ServiceType serviceType;

        private Boolean active;
      }

