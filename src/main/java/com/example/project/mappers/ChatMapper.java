package com.example.project.mappers;

import com.example.project.chats.CreateChatRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mapstruct.*;
import com.example.project.dtos.ChatDto;
import com.example.project.entities.Chat;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mappings({
            @Mapping(source = "user1.id", target = "user1Id"),
            @Mapping(source = "user2.id", target = "user2Id"),
            @Mapping(
                    target = "lastMessage",
                    expression = "java(chat.getMessages() == null || chat.getMessages().isEmpty() ? null : chat.getMessages().get(chat.getMessages().size() - 1).getContent())"
            )
    })
    ChatDto toDto(Chat chat);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user1", ignore = true) // هنحددهم في السيرفيس
    @Mapping(target = "user2", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Chat toEntity(CreateChatRequest request);
}