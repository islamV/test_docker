package com.example.project.repositories;

import com.example.project.entities.Question;
import com.example.project.entities.QuestionTopic;
import com.example.project.entities.ServiceType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuestionTopicId(Long questionTopicId);

    boolean existsByTextIgnoreCaseAndQuestionTopicId(String text, Long questionTopicId);

    List<Question> findByQuestionTopicServiceType(ServiceType serviceType);


}

