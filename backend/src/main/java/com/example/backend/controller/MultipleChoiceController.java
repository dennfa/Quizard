package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuizResponse;
import lombok.RequiredArgsConstructor;
import com.example.backend.models.NewMultipleChoiceQuiz;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.backend.services.MultipleChoiceService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MultipleChoiceController {

    private final MultipleChoiceService multipleChoiceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MultipleChoiceQuizResponse addQuiz(@RequestBody NewMultipleChoiceQuiz newMultipleChoiceQuiz){
        return multipleChoiceService.addQuiz(newMultipleChoiceQuiz);
    }
}
