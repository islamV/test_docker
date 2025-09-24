package com.example.project.mappers;

import com.example.project.dtos.ServiceDto;
import com.example.project.entities.ServiceEntity;
import com.example.project.services.CreateServiceRequest;
import com.example.project.services.UpdateServiceRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ServiceMapper {

    @Mapping(source = "createdBy", target = "createdById")
    ServiceDto toDto(ServiceEntity entity);


    @Mapping(target = "id", ignore = true)
    ServiceEntity toEntity(CreateServiceRequest request);

    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateServiceRequest request, @MappingTarget ServiceEntity entity);
}
