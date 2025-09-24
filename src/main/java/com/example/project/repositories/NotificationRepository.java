package com.example.project.repositories;

import com.example.project.entities.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
   Page<Notification> findByRead(boolean read, Pageable pageable);

    List<Notification> findByRead(Boolean Read);

}