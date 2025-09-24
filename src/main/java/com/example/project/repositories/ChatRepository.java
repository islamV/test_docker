package com.example.project.repositories;

import com.example.project.entities.Chat;
import com.example.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);
}