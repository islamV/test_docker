package com.example.project.services;


import com.example.project.dtos.ServiceDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    // GET /services?type=
    @GetMapping
    public List<ServiceDto> list(@RequestParam(value = "type", required = false) String type) {
        return serviceService.list(type);
    }

    // GET /services/{id}
    @GetMapping("/{id}")
    public ServiceDto getById(@PathVariable Long id) {
        return serviceService.getById(id);
    }

    // POST /services
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceDto create(@ModelAttribute CreateServiceRequest request) {
        return serviceService.create(request);
    }

    // PATCH /services/{id}
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ServiceDto update(@PathVariable Long id, @ModelAttribute UpdateServiceRequest request) {
        return serviceService.update(id, request);
    }

    // DELETE /services/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceService.delete(id);
    }
}
