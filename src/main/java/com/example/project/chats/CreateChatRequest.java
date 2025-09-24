package com.example.project.chats;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateChatRequest {

    @NotBlank
    private String title;
    @NotNull
    private Long participantId;
}