package com.example.backend.models.play;

import java.util.Set;

public record PlayMultipleChoiceQuestions(
        String question,
        Set<String> answers
) {
}
