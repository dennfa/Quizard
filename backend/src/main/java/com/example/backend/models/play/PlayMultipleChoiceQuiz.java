package com.example.backend.models.play;

import lombok.Builder;

import java.util.List;
@Builder
public record PlayMultipleChoiceQuiz(
        String id,
        String name,
        int numberOfQuestions,
        List<PlayMultipleChoiceQuestion> playMultipleChoiceQuestions
) {
}
