package com.example.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter

public class MessageDto {
    private Long id;
    private Long chatId;
    private Long senderId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

}
