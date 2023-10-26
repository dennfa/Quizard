package com.example.backend.models;

public record MultipleChoiceQuestion(
        String question,
        String falseAnswer,
        String trueAnswer
) {
}
