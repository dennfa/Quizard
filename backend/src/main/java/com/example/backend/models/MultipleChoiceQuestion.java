package com.example.backend.models;

import java.util.List;

public record MultipleChoiceQuestion(
        String question,
        List<String> falseAnswers,
        String trueAnswer
) {
    public MultipleChoiceQuestion {
        if (falseAnswers.size() != 3) {
            throw new IllegalArgumentException("There must be exactly 3 false answers");
        }
    }
}