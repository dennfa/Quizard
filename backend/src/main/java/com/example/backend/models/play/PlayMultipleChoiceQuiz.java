package com.example.backend.models.play;

import java.util.List;

public record PlayMultipleChoiceQuiz(
        String id,
        String name,
        String description,
        int numberOfQuestions,
        List<PlayMultipleChoiceQuestions> playMultipleChoiceQuestions
) {
}
