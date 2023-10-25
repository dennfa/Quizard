package com.example.backend.services.mappings;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.MultipleChoiceQuizResponse;
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

    public MultipleChoiceQuizResponse mapMcQuizToMcQuizResponse(MultipleChoiceQuiz mcq){
        return MultipleChoiceQuizResponse.builder()
                .id(mcq.id())
                .quizName(mcq.quizName())
                .multipleChoiceQuestions(mcq.multipleChoiceQuestions())
                .build();
    }
}
