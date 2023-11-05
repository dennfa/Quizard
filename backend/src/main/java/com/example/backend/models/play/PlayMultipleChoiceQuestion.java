package com.example.backend.models.play;

import lombok.Builder;

import java.util.List;

@Builder
public record PlayMultipleChoiceQuestion(
        String question,
        List<String> answers
) {
}
