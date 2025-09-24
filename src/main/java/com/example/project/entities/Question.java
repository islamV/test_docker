package com.example.project.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_topic_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_question__question_topic"))
    private QuestionTopic questionTopic;

    @Column(nullable = false, length = 500)
    private String text;
}
