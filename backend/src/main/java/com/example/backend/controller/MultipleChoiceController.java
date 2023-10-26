package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuiz;
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
    public MultipleChoiceQuiz addQuiz(@RequestBody NewMultipleChoiceQuiz newMultipleChoiceQuiz){
        return multipleChoiceService.addQuiz(newMultipleChoiceQuiz);
    }
}
