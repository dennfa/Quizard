package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MultipleChoiceService {

    private final MultipleChoiceRepo multipleChoiceRepo;
    private final MultipleChoiceMappingService mcms;

    public MultipleChoiceQuiz addQuiz(MultipleChoiceQuiz multipleChoiceQuiz) {
        return multipleChoiceRepo.save(mcms.addAuthorToMultipleChoiceQuiz(multipleChoiceQuiz));
    }

    public List<MultipleChoiceQuiz> getAllQuizzes() {
        return multipleChoiceRepo.findAll();
    }

    public List<PlayMultipleChoiceQuiz> getAllPlayQuizzes() {
        return multipleChoiceRepo.findAll().stream()
                .map(mcms::mapMultipleChoiceQuizToPlayMcq)
                .toList();
    }

    public MultipleChoiceQuiz getQuizById(String id) {
        return multipleChoiceRepo.findById(id).orElseThrow();
    }

    public PlayMultipleChoiceQuiz getPlayQuizById(String id) {
        MultipleChoiceQuiz mcq = multipleChoiceRepo.findById(id).orElseThrow();
        return mcms.mapMultipleChoiceQuizToPlayMcq(mcq);
    }

    public MultipleChoiceQuiz updateQuiz(MultipleChoiceQuiz multipleChoiceQuiz){
        if(multipleChoiceRepo.findById(multipleChoiceQuiz.id()).isEmpty()) throw new NoSuchElementException();
        return multipleChoiceRepo.save(multipleChoiceQuiz);
    }

    public void deleteQuiz( String id){
        multipleChoiceRepo.deleteById(id);
    }

    public String getCorrectAnswer(String id, String index) {
        return multipleChoiceRepo.findById(id)
                .orElseThrow().multipleChoiceQuestions().get(Integer.parseInt(index)).trueAnswer();
    }
}
