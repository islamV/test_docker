package com.example.project.questionTopic;

import com.example.project.dtos.QuestionTopicDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/question-topics")
@RequiredArgsConstructor
public class QuestionTopicController {

    private final QuestionTopicService questionTopicService;

    // GET /question-topics?type=
    @GetMapping
    public ResponseEntity<List<QuestionTopicDto>> list(@RequestParam(required = false) String type) {
        List<QuestionTopicDto> topics = questionTopicService.list(type);
        return ResponseEntity.ok(topics);
    }

    // GET /question-topics/{id}
    @GetMapping("/{id}")
    public ResponseEntity<QuestionTopicDto> getById(@PathVariable Long id) {
        QuestionTopicDto dto = questionTopicService.getById(id);
        return ResponseEntity.ok(dto);
    }

    // POST /question-topics
    @PostMapping
    public ResponseEntity<QuestionTopicDto> create(@Valid @RequestBody CreateQuestionTopicRequest request,
                                                   UriComponentsBuilder uriBuilder) {
        QuestionTopicDto created = questionTopicService.create(request);
        URI location = uriBuilder.path("/question-topics/{id}").buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(created);
    }

    // PATCH /question-topics/{id}
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionTopicDto> update(@PathVariable Long id,
                                                   @Valid @RequestBody UpdateQuestionTopicRequest request) {
        QuestionTopicDto updated = questionTopicService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE /question-topics/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionTopicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

