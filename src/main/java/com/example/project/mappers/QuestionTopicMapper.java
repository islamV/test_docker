package com.example.project.mappers;

import com.example.project.dtos.QuestionTopicDto;
import com.example.project.entities.Question;
import com.example.project.questionTopic.CreateQuestionTopicRequest;
import com.example.project.questionTopic.UpdateQuestionTopicRequest;
import org.mapstruct.*;
import com.example.project.entities.QuestionTopic;

import java.util.List;

@Mapper(componentModel = "spring"
,        uses = { QuestionMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface QuestionTopicMapper {
    QuestionTopicDto toDto(QuestionTopic topic);


    @Mapping(target = "id", ignore = true)
    QuestionTopic toEntity(CreateQuestionTopicRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UpdateQuestionTopicRequest request, @MappingTarget QuestionTopic entity);
}
