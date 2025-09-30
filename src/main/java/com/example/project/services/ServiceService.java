package com.example.project.services;



import com.example.project.dtos.ServiceDto;
import com.example.project.dtos.UserDto;
import com.example.project.entities.ServiceEntity;
import com.example.project.entities.ServiceType;
import com.example.project.mappers.ServiceMapper;
import com.example.project.repositories.ServiceRepository;
import com.example.project.upload.service.FilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper mapper;
    private final FilesStorageService filesStorageService;

    // GET /services?type=
    public List<ServiceDto> list(String type) {
        if (type != null && !type.isBlank()) {
            ServiceType typeEnum = parseType(type);
            return serviceRepository.findByServiceType(typeEnum)
                    .stream().map(mapper::toDto).toList();
        }
        return serviceRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public ServiceDto getById(Long id) {
        ServiceEntity entity = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));
        return mapper.toDto(entity);
    }

    public ServiceDto create(CreateServiceRequest request) {
        ServiceType typeEnum = parseType(request.getType());
        if (serviceRepository.existsByNameIgnoreCaseAndServiceType(request.getName(), typeEnum)) {
            throw new DuplicateServiceException(dupMsg(request.getName(), typeEnum));
        }
        String photoUrl = null;
        if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
            String originalFileName = request.getPhoto().getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFileName;
            filesStorageService.save(request.getPhoto() , fileName);


            photoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(fileName)
                    .toUriString();
        }
        ServiceEntity entity = mapper.toEntity(request);
        entity.setServiceType(typeEnum);
        entity.setPhotoUrl(photoUrl);
        ServiceEntity saved = serviceRepository.save(entity);
        return mapper.toDto(saved);
    }


    @Transactional
    public ServiceDto update(Long id, UpdateServiceRequest request) {
        ServiceEntity entity = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(id));
        mapper.update(request, entity);

        if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
            String originalFileName = request.getPhoto().getOriginalFilename();
            String fileName = UUID.randomUUID() + "_" + originalFileName;
            filesStorageService.save(request.getPhoto() , fileName);
            String photoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/files/")
                    .path(fileName)
                    .toUriString();
            entity.setPhotoUrl(photoUrl);
        }
        ServiceEntity saved = serviceRepository.save(entity);
        return mapper.toDto(saved);
    }


    public void delete(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException(id);
        }
        serviceRepository.deleteById(id);
    }

    // ---- Helpers ----
    private ServiceType parseType(String type) {
        try {
            return ServiceType.valueOf(type.trim().toUpperCase());
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Invalid service type: " + type + ". Allowed: HOSPITAL, HOTEL, CLUB, CAFE"
            );
        }
    }

    private String dupMsg(String name, ServiceType type) {
        return "Service '" + name + "' (type: " + type.name().toLowerCase() + ") already exists.";
    }
}
