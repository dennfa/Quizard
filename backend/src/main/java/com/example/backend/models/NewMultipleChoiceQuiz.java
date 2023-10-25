package com.example.backend.models;

import java.util.List;

public record NewMultipleChoiceQuiz(
        String quizName,
        List<MultipleChoiceQuestion> multipleChoiceQuestions
) {
}
