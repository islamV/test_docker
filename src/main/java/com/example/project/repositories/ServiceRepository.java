package com.example.project.repositories;

import com.example.project.entities.ServiceEntity;
import com.example.project.entities.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    @Query("SELECT s FROM ServiceEntity s WHERE s.serviceType = :type")
    Optional<ServiceEntity> findByType(@Param("type") ServiceType type);

    List<ServiceEntity> findByServiceType(ServiceType serviceType);
    boolean existsByNameIgnoreCaseAndServiceType(String name, ServiceType serviceType);
    boolean existsByNameIgnoreCaseAndServiceTypeAndIdNot(String name, ServiceType serviceType, Long id);
}


