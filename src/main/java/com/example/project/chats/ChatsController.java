package com.example.project.chats;

import com.example.project.dtos.MessageDto;
import com.example.project.dtos.UserDto;
import com.example.project.entities.User;
import com.example.project.dtos.ChatDto;
import com.example.project.mappers.UserMapper;
import com.example.project.repositories.UserRepository;
import com.example.project.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatsController {

    private final ChatsService chatsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private Long getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (Long) auth.getPrincipal();

    }

    @GetMapping
    public List<ChatDto> getChats() {
        return chatsService.getChats(getCurrentUser());
    }

    @PostMapping
    public ChatDto createChat(@RequestBody @Valid CreateChatRequest request) {
        return chatsService.createChat(getCurrentUser(), request);
    }

    @GetMapping("/{chatId}/messages")
    public List<MessageDto> getMessages(@PathVariable Long chatId) {
        return chatsService.getMessages(chatId);
    }

    @PostMapping("/{chatId}/messages")
    public MessageDto sendMessage(@PathVariable Long chatId,
                                  @RequestBody @Valid SendMessageRequest request) {
        return chatsService.sendMessage(chatId, getCurrentUser(), request);
    }

    @GetMapping("/available-users")
    public List<UserDto> getAvailableUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Long currentUserId = getCurrentUser();

        return chatsService.getAvailableUsersForChat(currentUserId, page, size)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }




    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chatsService.delete(id);
    }
}
