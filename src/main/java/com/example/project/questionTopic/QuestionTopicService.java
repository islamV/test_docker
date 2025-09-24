package com.example.project.questionTopic;

import com.example.project.dtos.QuestionTopicDto;
import com.example.project.entities.QuestionTopic;
import com.example.project.entities.ServiceType;
import com.example.project.mappers.QuestionTopicMapper;
import com.example.project.repositories.QuestionTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTopicService {

    private final QuestionTopicRepository questionTopicRepository;
    private final QuestionTopicMapper questionTopicMapper;

    // GET /question-topics?type=
    public List<QuestionTopicDto> list(String type) {
        if (type != null && !type.isBlank()) {
            ServiceType serviceType = parseType(type);
            return questionTopicRepository.findByServiceType(serviceType)
                    .stream()
                    .map(questionTopicMapper::toDto)
                    .toList();
        }
        return questionTopicRepository.findAll()
                .stream()
                .map(questionTopicMapper::toDto)
                .toList();
    }

    // GET /question-topics/{id}
    public QuestionTopicDto getById(Long id) {
        QuestionTopic questionTopic = questionTopicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));
        return questionTopicMapper.toDto(questionTopic);
    }

    // POST /question-topics
    @Transactional
    public QuestionTopicDto create(CreateQuestionTopicRequest request) {
        String normalizedTitle = normalizeSpaces(request.getTitle());
        ServiceType serviceType = request.getServiceType();

        if (questionTopicRepository.existsByTitleIgnoreCaseAndServiceType(normalizedTitle, serviceType)) {
            throw new DuplicateTopicException();
        }
        QuestionTopic questionTopic = questionTopicMapper.toEntity(request);
        questionTopic.setTitle(normalizedTitle);
        questionTopic.setServiceType(serviceType);

        QuestionTopic saved = questionTopicRepository.save(questionTopic);
        return questionTopicMapper.toDto(saved);
    }

    // PATCH /question-topics/{id}
    @Transactional
    public QuestionTopicDto update(Long id, UpdateQuestionTopicRequest request) {
        QuestionTopic questionTopic = questionTopicRepository.findById(id)
                .orElseThrow(() -> new TopicNotFoundException(id));

        String finalTitle = (request.getTitle() != null)
                ? normalizeSpaces(request.getTitle())
                : questionTopic.getTitle();

         if (questionTopicRepository.existsByTitleIgnoreCaseAndIdNot(finalTitle, id)) {
            throw new DuplicateTopicException();
        }
        questionTopicMapper.update(request, questionTopic);
        questionTopic.setTitle(finalTitle);


        QuestionTopic saved = questionTopicRepository.save(questionTopic);
        return questionTopicMapper.toDto(saved);
    }

    // DELETE /question-topics/{id}
    @Transactional
    public void delete(Long id) {
        if (!questionTopicRepository.existsById(id)) {
            throw new TopicNotFoundException(id);
        }
        questionTopicRepository.deleteById(id);
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

    private String normalizeSpaces(String input) {
        return (input == null) ? null : input.trim().replaceAll("\\s+", " ");
    }
}

