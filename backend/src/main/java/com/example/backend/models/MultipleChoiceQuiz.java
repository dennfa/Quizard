package com.example.backend.models;

import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Builder
public record MultipleChoiceQuiz(
        @MongoId
        String id,
        @With
        String name,
        String description,
        int numberOfQuestions,
        List<MultipleChoiceQuestion> multipleChoiceQuestions
) {
}
