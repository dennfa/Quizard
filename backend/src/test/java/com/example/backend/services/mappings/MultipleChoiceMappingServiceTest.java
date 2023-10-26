package com.example.backend.services.mappings;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.NewMultipleChoiceQuiz;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceMappingServiceTest {

    private final MultipleChoiceMappingService mcms = new MultipleChoiceMappingService();


    @Test
    void mapNewMCQuizToMCQuiz() {
        //GIVEN
        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion("hi?", "no", "yeah");
        List<MultipleChoiceQuestion> multipleChoiceQuestions = List.of(multipleChoiceQuestion);
        NewMultipleChoiceQuiz newMultipleChoiceQuiz = new NewMultipleChoiceQuiz("quiz", multipleChoiceQuestions);

        //WHEN
        MultipleChoiceQuiz actual = mcms.mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz);

        //THEN
        assertEquals(multipleChoiceQuestions, actual.multipleChoiceQuestions());
        assertEquals(newMultipleChoiceQuiz.quizName(), actual.quizName());
        assertNull(actual.id());
    }
}