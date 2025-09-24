package com.example.project.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuestionTopic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Column(name = "name", nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "questionTopic")
    private List<Question> questions = new ArrayList<>();



}
