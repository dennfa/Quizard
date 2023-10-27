package com.example.backend.models;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
public record MultipleChoiceQuiz(
        @MongoId
        String id,
        String name,
        String description,
        int numberOfQuestions,
        List<MultipleChoiceQuestion> multipleChoiceQuestions
) {
}
