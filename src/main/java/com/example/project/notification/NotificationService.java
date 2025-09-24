package com.example.project.notification;



import com.example.project.dtos.NotificationDto;
import com.example.project.entities.Notification;
import com.example.project.mappers.NotificationMapper;
import com.example.project.repositories.NotificationRepository;
import com.example.project.repositories.ReportRepository;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserRepository userRepository;
    private  final ReportRepository reportRepository;


    @Transactional
    public NotificationDto create(CreateNotificationRequest req) {
        Notification entity = notificationMapper.toEntity(req);
        entity.setUser(userRepository.getReferenceById(req.userId()));
        entity.setReport(reportRepository.getReferenceById(req.reportId()));

        Notification saved = notificationRepository.save(entity);
        return notificationMapper.toDto(saved);
    }


    public Page<NotificationDto> list(Boolean isRead, Pageable pageable) {
        Page<Notification> page = (isRead != null)
                ? notificationRepository.findByRead(isRead, pageable)
                : notificationRepository.findAll(pageable);

        return page.map(notificationMapper::toDto);
    }

    @Transactional
    public NotificationDto markAsRead(Long id) {
        Notification entity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));

        if (!entity.isRead()) {
            entity.setRead(true);
        }
        return notificationMapper.toDto(entity);
    }
}

