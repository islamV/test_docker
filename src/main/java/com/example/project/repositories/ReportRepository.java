package com.example.project.repositories;

import com.example.project.entities.Report;
import com.example.project.entities.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("""
        SELECT r FROM Report r
        JOIN r.service s
        WHERE (:serviceType IS NULL OR s.serviceType = :serviceType)
          AND (:from IS NULL OR r.createdAt >= :from)
          AND (:to   IS NULL OR r.createdAt <= :to)
    """)
    Page<Report> search(@Param("serviceType") ServiceType serviceType,
                        @Param("from") Instant from,
                        @Param("to") Instant to,
                        Pageable pageable);
}
