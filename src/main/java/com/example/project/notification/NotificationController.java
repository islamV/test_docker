package com.example.project.notification;



import com.example.project.dtos.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class NotificationController {

    private final NotificationService notificationService;



    @PostMapping("/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationDto create(@Valid @RequestBody CreateNotificationRequest req) {
        return notificationService.create(req);
    }

    // GET /notifications?is_read=false&page=0&size=20&sort=id,desc
    @GetMapping("/notifications")
    public Page<NotificationDto> list(
            @RequestParam(name = "is_read", required = false) Boolean isRead,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return notificationService.list(isRead, pageable);
    }

    // PATCH /notifications/{id}/read
    @PatchMapping("/notifications/{id}/read")
    public NotificationDto markAsRead(@PathVariable Long id) {
        return notificationService.markAsRead(id);
    }
}
