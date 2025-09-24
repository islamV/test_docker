package com.example.project.mappers;

import com.example.project.question.QuestionCreateRequest;
import com.example.project.question.QuestionUpdateRequest;
import org.mapstruct.*;
import com.example.project.dtos.QuestionDto;
import com.example.project.entities.Question;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {


    @Mapping(source = "questionTopic.id", target = "questionTopicId")
    QuestionDto toDto(Question entity);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "questionTopicId", target = "questionTopic.id")
    Question toEntity(QuestionCreateRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "questionTopicId", target = "questionTopic.id")
    void update(QuestionUpdateRequest request, @MappingTarget Question entity);
}
