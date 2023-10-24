package models;

import java.util.Set;

public record MultipleChoiceQuestion(
        String question,
        Set<String> falseAnswers,
        String trueAnswer
) {
}
