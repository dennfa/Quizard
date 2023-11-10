package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
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
    public MultipleChoiceQuiz addQuiz(@RequestBody MultipleChoiceQuiz multipleChoiceQuiz) {
        return multipleChoiceService.addQuiz(multipleChoiceQuiz);
    }

    @PostMapping("/take/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getCorrectAnswer(@PathVariable String id, @RequestBody String index) {
        return multipleChoiceService.getCorrectAnswer(id,index);
    }

    @GetMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public List<MultipleChoiceQuiz> getAllQuizzes() {
        return multipleChoiceService.getAllQuizzes();
    }

    @GetMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MultipleChoiceQuiz getQuizById(@PathVariable String id) {

        return multipleChoiceService.getQuizById(id);
    }

    @GetMapping("/take")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayMultipleChoiceQuiz> getAllPlayQuizzes() {
        return multipleChoiceService.getAllPlayQuizzes();
    }

    @GetMapping("/take/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayMultipleChoiceQuiz getPlayQuizById(@PathVariable String id) {

        return multipleChoiceService.getPlayQuizById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public MultipleChoiceQuiz updateQuiz(@RequestBody MultipleChoiceQuiz multipleChoiceQuiz) {

        return multipleChoiceService.updateQuiz(multipleChoiceQuiz);
    }

    @DeleteMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuiz(@PathVariable String id) {
        multipleChoiceService.deleteQuiz(id);
    }
}
