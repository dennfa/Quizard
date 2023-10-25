package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuiz;
import lombok.RequiredArgsConstructor;
import com.example.backend.models.NewMultipleChoiceQuiz;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.services.MultipleChoiceService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MultipleChoiceController {

    private final MultipleChoiceService multipleChoiceService;

    @PostMapping("/create")
    public MultipleChoiceQuiz addQuiz(@RequestBody NewMultipleChoiceQuiz newMultipleChoiceQuiz){
        return multipleChoiceService.addQuiz(newMultipleChoiceQuiz);
    }
}
