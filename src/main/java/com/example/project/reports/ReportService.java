package com.example.project.reports;

import com.example.project.common.BadRequestException;
import com.example.project.dtos.ReportDto;
import com.example.project.entities.JsonHelper;
import com.example.project.entities.Report;
import com.example.project.entities.ServiceType;
import com.example.project.mappers.ReportMapper;
import com.example.project.repositories.ReportRepository;
import com.example.project.repositories.ServiceRepository;
import com.example.project.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ServiceRepository serviceRepository;
    private final JsonHelper jsonHelper;
    private final UserRepository userRepository;

    // GET /reports?service_type=&from=&to=&page=&size=
    @Transactional(readOnly = true)
    public Page<ReportDto> list(ServiceType serviceType,
                                Instant from, Instant to, Integer page, Integer size) {
        int p = page == null ? 0 : Math.max(page, 0);
        int s = size == null ? 20 : Math.min(Math.max(size, 1), 100);

        Pageable pageable = PageRequest.of(p, s, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reportRepository.search(serviceType, from, to, pageable)
                .map(e -> reportMapper.toDto(e, jsonHelper));
    }

    @Transactional(readOnly = true)
    public ReportDto getById(Long id) {
        Report entity = reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
        return reportMapper.toDto(entity, jsonHelper);
    }

    @Transactional
    public ReportDto create(CreateReportRequest request) {
        Report entity = reportMapper.toEntity(request, jsonHelper);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        var officer = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Authenticated user not found: " + userId));
        entity.setOfficer(officer);

        entity = reportRepository.save(entity);
        return reportMapper.toDto(entity, jsonHelper);
    }

    @Transactional
    public void delete(Long id) {
        if (!reportRepository.existsById(id)) {
            throw new ReportNotFoundException(id);
        }
        reportRepository.deleteById(id);
    }
}
