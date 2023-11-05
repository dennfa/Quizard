package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuestion;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MultipleChoiceMappingService {

    public PlayMultipleChoiceQuiz mapMultipleChoiceQuizToPlayMcq(MultipleChoiceQuiz mcq) {

        List<PlayMultipleChoiceQuestion> pmcqList = mcq.multipleChoiceQuestions()
                .stream().map(mcQuestion -> {
                            List<String> answers = new ArrayList<>(List.of(mcQuestion.falseAnswer(), mcQuestion.trueAnswer()));
                            Collections.shuffle(answers);

                            return PlayMultipleChoiceQuestion.builder()
                                    .question(mcQuestion.question())
                                    .answers(answers)
                                    .build();
                        }
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
