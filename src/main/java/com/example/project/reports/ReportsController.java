package com.example.project.reports;




import com.example.project.dtos.ReportDto;
import com.example.project.entities.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportService reportService;

    // GET /reports?service_type=&from=&to=&page=&size=
    @GetMapping
    public Page<ReportDto> list(
            @RequestParam(name = "service_type", required = false) ServiceType serviceType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return reportService.list(serviceType, from, to, page, size);
    }

    // GET /reports/{id}
    @GetMapping("/{id}")
    public ReportDto getById(@PathVariable Long id) {
        return reportService.getById(id);
    }

    // POST /reports
    @PostMapping
    public ReportDto create(@RequestBody CreateReportRequest request) {
        return reportService.create(request);
    }

    // DELETE /reports/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reportService.delete(id);
    }
}

