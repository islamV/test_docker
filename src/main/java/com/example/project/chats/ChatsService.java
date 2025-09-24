package com.example.project.chats;
import com.example.project.dtos.ChatDto;
import com.example.project.dtos.MessageDto;
import com.example.project.entities.Chat;
import com.example.project.entities.Message;
import com.example.project.entities.User;
import com.example.project.mappers.ChatMapper;
import com.example.project.mappers.MessageMapper;
import com.example.project.reports.ReportNotFoundException;
import com.example.project.repositories.ChatRepository;
import com.example.project.repositories.MessageRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;

    public List<ChatDto> getChats(Long userId) {
        return chatRepository.findByUser1IdOrUser2Id(userId, userId).stream()
                .map(chatMapper::toDto)
                .toList();
    }

    public ChatDto createChat(Long currentUserId, CreateChatRequest request) {
        User user1 = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException(currentUserId));

        User user2 = userRepository.findById(request.getParticipantId())
                .orElseThrow(() -> new UserNotFoundException(request.getParticipantId()));

        Chat chat = chatMapper.toEntity(request);
        chat.setUser1(user1);
        chat.setUser2(user2);
        chat.setCreatedAt(Instant.now());
        chat.setUpdatedAt(Instant.now());

        Chat saved = chatRepository.save(chat);
        return chatMapper.toDto(saved);
    }

    public List<MessageDto> getMessages(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));

        return messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId()).stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public MessageDto sendMessage(Long chatId, Long senderId, SendMessageRequest request) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ChatNotFoundException(chatId));

        // تأكد إن المرسل أحد طرفي الشات
        if (!chat.getUser1().getId().equals(senderId) && !chat.getUser2().getId().equals(senderId)) {
            throw new IllegalStateException("You are not a participant in this chat");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException(senderId));

        Message message = messageMapper.toEntity(request);
        message.setChat(chat);
        message.setSender(sender);
        message.setCreatedAt(Instant.now());

        messageRepository.save(message);
        return messageMapper.toDto(message);
    }

    public List<User> getAvailableUsersForChat(Long currentUserId, int page, int size) {
        List<User> allUsers = userRepository.findAll();
        allUsers.removeIf(u -> u.getId().equals(currentUserId));
        List<Chat> existingChats = chatRepository.findByUser1IdOrUser2Id(currentUserId, currentUserId);
        existingChats.forEach(chat -> {
            allUsers.removeIf(u -> u.getId().equals(chat.getUser1().getId()) || u.getId().equals(chat.getUser2().getId()));
        });

        int fromIndex = Math.min(page * size, allUsers.size());
        int toIndex = Math.min(fromIndex + size, allUsers.size());

        return allUsers.subList(fromIndex, toIndex);
    }


    @Transactional
    public void delete(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new ReportNotFoundException(id);
        }
        chatRepository.deleteById(id);
    }

}
