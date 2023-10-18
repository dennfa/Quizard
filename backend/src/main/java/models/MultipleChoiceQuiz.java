package models;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public record MultipleChoiceQuiz(
        @MongoId
        String id,
        String quizName,
        String quizDescription,
        List<MultipleChoiceQuestion> questions
) {
}
