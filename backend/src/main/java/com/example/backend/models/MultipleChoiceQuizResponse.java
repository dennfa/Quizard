package com.example.backend.models;

import lombok.Builder;

import java.util.List;

@Builder
public record MultipleChoiceQuizResponse(
        String id,
        String quizName,
        List<MultipleChoiceQuestion> multipleChoiceQuestions
) {
}
