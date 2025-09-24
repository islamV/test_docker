package com.example.project.mappers;



import com.example.project.dtos.NotificationDto;
import com.example.project.entities.Notification;
import com.example.project.notification.CreateNotificationRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NotificationMapper {


    @Mappings({
            @Mapping(target = "userId",  source = "user.id"),
            @Mapping(target = "reportId", source = "report.id"),
         //   @Mapping(target = "read",   source = "read")

            @Mapping(target = "data",     source = "data")
    })
    NotificationDto toDto(Notification entity);

    @Mappings({
            @Mapping(target = "id",     ignore = true),
            @Mapping(target = "user",   ignore = true),
            @Mapping(target = "report", ignore = true),
            @Mapping(target = "read",   constant = "false"),
            @Mapping(target = "data",   source = "data")
    })
    Notification toEntity(CreateNotificationRequest req);



    default JsonNode map(String value) {
        if (value == null || value.isBlank()) return NullNode.getInstance();
        try {
            return new ObjectMapper().readTree(value);
        } catch (Exception e) {
            return NullNode.getInstance();
        }
    }

    default String map(JsonNode node) {
        if (node == null || node.isNull()) return null;
        return node.toString();
    }
}
