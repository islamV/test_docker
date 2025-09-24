package com.example.project.mappers;
import com.example.project.dtos.ReportDto;
import com.example.project.entities.Report;
import com.example.project.entities.JsonHelper;
import com.example.project.reports.CreateReportRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "officer", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "service.serviceType",source = "serviceType")
   // @Mapping(target = "service.name",source = "serviceName")
    @Mapping(target = "service.id",source = "serviceId")
    @Mapping(target = "answersJson", expression = "java(jsonToString(request.data(), helper))")
    Report toEntity(CreateReportRequest request, @Context JsonHelper helper);

    //  @Mapping(target = "serviceType", source = "service.serviceType")
    @Mapping(target = "serviceName"  , source = "service.name")
    @Mapping(target = "createdByName", source = "officer.fullName")
    @Mapping(target = "createdAt", source = "createdAt")
  //  @Mapping(target = "data", expression = "java(stringToJson(entity.getAnswersJson(), helper))")
    ReportDto toDto(Report entity, @Context JsonHelper helper);

    default String jsonToString(JsonNode node, @Context JsonHelper helper) {
        return helper.toJsonString(node);
    }

    default JsonNode stringToJson(String s, @Context JsonHelper helper) {
        return helper.toJsonNode(s);
    }
}