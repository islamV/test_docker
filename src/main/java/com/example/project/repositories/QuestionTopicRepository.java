package com.example.project.repositories;

import com.example.project.entities.QuestionTopic;
import com.example.project.entities.ServiceType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionTopicRepository extends JpaRepository<QuestionTopic, Long> {
    List<QuestionTopic> findByServiceTypeAndActiveTrue(ServiceType type);

    List<QuestionTopic> findByServiceType(ServiceType serviceType);

    boolean existsByTitleIgnoreCaseAndServiceType(String title, ServiceType serviceType);

    boolean existsByTitleIgnoreCaseAndIdNot(String title, Long id);

    @EntityGraph(attributePaths = "questions")
    @Query("select qt from QuestionTopic qt where qt.id = :id")
    Optional<QuestionTopic> findByIdWithQuestions(@Param("id") Long id);

    @EntityGraph(attributePaths = "questions")
    @Query("select qt from QuestionTopic qt")
    List<QuestionTopic> findAllWithQuestions();
    @Query("""
       select distinct qt
       from QuestionTopic qt
       left join fetch qt.questions
       where qt.serviceType = :serviceType
       """)
    List<QuestionTopic> findAllWithQuestionsByServiceType(@Param("serviceType") ServiceType serviceType);


}