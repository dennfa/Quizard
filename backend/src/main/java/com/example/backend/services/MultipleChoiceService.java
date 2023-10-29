package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MultipleChoiceService {

    private final MultipleChoiceRepo multipleChoiceRepo;

    public MultipleChoiceQuiz addQuiz(MultipleChoiceQuiz multipleChoiceQuiz) {
        return multipleChoiceRepo.save(multipleChoiceQuiz);
    }

    public List<MultipleChoiceQuiz> getAllQuizzes() {
        return multipleChoiceRepo.findAll();
    }

    public MultipleChoiceQuiz getQuizById(String id) {
        return multipleChoiceRepo.findById(id).orElseThrow();
    }
}
