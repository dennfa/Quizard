package com.example.backend.models;

import java.util.List;

public record NewMultipleChoiceQuiz(
        String name,
        String description,
        int numberOfQuestions,
        List<MultipleChoiceQuestion> multipleChoiceQuestions
) {
}
