package com.example.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor


public class ChatDto {
    private Long id;
    private Long user1Id;
    private Long user2Id;
    private String lastMessage;
    private Instant createdAt;
    private Instant updatedAt;
}
