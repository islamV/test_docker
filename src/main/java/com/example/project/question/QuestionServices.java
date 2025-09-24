package com.example.project.question;

import com.example.project.dtos.QuestionDto;
import com.example.project.dtos.QuestionTopicDto;
import com.example.project.entities.Question;
import com.example.project.entities.QuestionTopic;
import com.example.project.mappers.QuestionMapper;
import com.example.project.mappers.QuestionTopicMapper;
import com.example.project.questionTopic.TopicNotFoundException;
import com.example.project.repositories.QuestionRepository;
import com.example.project.repositories.QuestionTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServices {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuestionTopicRepository questionTopicRepository;
    private final QuestionTopicMapper questionTopicMapper;

    // GET /questions?questionTopicId=
    public List<QuestionDto> list(Long questionTopicId) {
        List<Question> questionEntities =
                (questionTopicId != null)
                        ? questionRepository.findByQuestionTopicId(questionTopicId)
                        : questionRepository.findAll();

        return questionEntities.stream()
                .map(questionMapper::toDto)
                .toList();
    }

    // GET /questions/{id}
    public QuestionDto getById(Long id) {
        Question questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        return questionMapper.toDto(questionEntity);
    }

    // POST /questions
    public QuestionDto create(QuestionCreateRequest request) {
        String normalizedText = request.getText().trim();

        if (questionRepository.existsByTextIgnoreCaseAndQuestionTopicId(
                normalizedText, request.getQuestionTopicId())) {
            throw new DuplicateQuestionException();
        }

        Question questionEntity = questionMapper.toEntity(request);
        questionEntity.setText(normalizedText);
        Question savedQuestion = questionRepository.save(questionEntity);
        return questionMapper.toDto(savedQuestion);
    }

    // PATCH /questions/{id}
    public QuestionDto update(Long id, QuestionUpdateRequest request) {
        Question questionEntity = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
        questionMapper.update(request, questionEntity);

        if (request.getText() != null) {
            questionEntity.setText(request.getText().trim());
        }

        Question savedQuestion = questionRepository.save(questionEntity);
        return questionMapper.toDto(savedQuestion);
    }

    // DELETE /questions/{id}
    public void delete(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException(id);
        }
        questionRepository.deleteById(id);
    }


    // GET /question-bank?service-type=HOSPITAL
    public List<QuestionTopicDto> questionBankByServiceType(String serviceTypeParam) {
        if (serviceTypeParam == null || serviceTypeParam.isBlank()) {
            return questionTopicRepository.findAllWithQuestions().stream()
                    .map(questionTopicMapper::toDto)
                    .toList();
        }
        var serviceType = java.util.Arrays.stream(com.example.project.entities.ServiceType.values())
                .filter(v -> v.name().equalsIgnoreCase(serviceTypeParam.trim()))
                .findFirst()
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.BAD_REQUEST,
                        "param 'service-type' must be one of: HOSPITAL, HOTEL, CLUB, CAFE"));

        return questionTopicRepository.findAllWithQuestionsByServiceType(serviceType).stream()
                .map(questionTopicMapper::toDto)
                .toList();
    }


}



