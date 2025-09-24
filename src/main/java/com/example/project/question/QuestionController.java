package com.example.project.question;
import com.example.project.dtos.QuestionDto;
import com.example.project.dtos.QuestionTopicDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class QuestionController {

    private final QuestionServices questionServices;

    // GET /questions?questionTopicId=
    @GetMapping("/questions")
    public List<QuestionDto> list(@RequestParam(name = "questionTopicId", required = false) Long questionTopicId) {
        return questionServices.list(questionTopicId);
    }

    // GET /questions/{id}
    @GetMapping("/questions/{id}")
    public QuestionDto getById(@PathVariable Long id) {
        return questionServices.getById(id);
    }

    // POST /questions
    @PostMapping("/questions")
    public QuestionDto create(@Valid @RequestBody QuestionCreateRequest request) {
        return questionServices.create(request);
    }

    // PATCH /questions/{id}
    @PatchMapping("/questions/{id}")
    public QuestionDto update(@PathVariable Long id, @RequestBody QuestionUpdateRequest request) {
        return questionServices.update(id, request);
    }

    // DELETE /questions/{id}
    @DeleteMapping("/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        questionServices.delete(id);
    }

    // GET /question-bank?service-type=HOSPITAL
    @GetMapping("/question-bank")
    public List<QuestionTopicDto> questionBank(
            @RequestParam(name = "service-type", required = false) String serviceType
    ) {
        return questionServices.questionBankByServiceType(serviceType);
    }


}
