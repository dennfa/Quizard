package com.example.backend.services.mappings;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.NewMultipleChoiceQuiz;
import org.springframework.stereotype.Service;

@Service
public class MultipleChoiceMappingService {

    public MultipleChoiceQuiz mapNewMCQuizToMCQuiz(NewMultipleChoiceQuiz nmc){
       return MultipleChoiceQuiz.builder()
               .quizName(nmc.quizName())
               .multipleChoiceQuestions(nmc.multipleChoiceQuestions())
               .build();
    }
}
