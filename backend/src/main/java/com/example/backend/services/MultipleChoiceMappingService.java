package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuestion;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class MultipleChoiceMappingService {

    public PlayMultipleChoiceQuiz mapMultipleChoiceQuizToPlayMcq(MultipleChoiceQuiz mcq) {

        List<PlayMultipleChoiceQuestion> pmcqList = mcq.multipleChoiceQuestions()
                .stream().map(mcqQuestion ->
                        PlayMultipleChoiceQuestion.builder()
                                .question(mcqQuestion.question())
                                .answers(Set.of(mcqQuestion.falseAnswer(), mcqQuestion.trueAnswer()))
                                .build()
                ).toList();


        return PlayMultipleChoiceQuiz.builder()
                .id(mcq.id())
                .name(mcq.name())
                .description(mcq.description())
                .numberOfQuestions(mcq.numberOfQuestions())
                .playMultipleChoiceQuestions(pmcqList)
                .build();
    }
}
