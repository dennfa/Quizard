package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.backend.services.MultipleChoiceService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MultipleChoiceController {

    private final MultipleChoiceService multipleChoiceService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MultipleChoiceQuiz addQuiz(@RequestBody MultipleChoiceQuiz multipleChoiceQuiz){
        return multipleChoiceService.addQuiz(multipleChoiceQuiz);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MultipleChoiceQuiz> getAllQuizzes(){
        return multipleChoiceService.getAllQuizzes();
    }
}
