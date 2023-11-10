package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultipleChoiceMappingServiceTest {

    AuthenticationService authService = mock(AuthenticationService.class);
    MultipleChoiceMappingService mcms = new MultipleChoiceMappingService(authService);

    @Test
    void mapMultipleChoiceQuizToPlayMcq() {
        //GIVEN
        MultipleChoiceQuiz given = new MultipleChoiceQuiz("1", "author", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1","2","3"), "ta")));

        //WHEN
        PlayMultipleChoiceQuiz actual = mcms.mapMultipleChoiceQuizToPlayMcq(given);

        //THEN
        assertEquals(given.id(), actual.id());
        assertEquals(given.author(), actual.author());
        assertEquals(given.name(), actual.name());
        assertEquals(given.numberOfQuestions(), actual.numberOfQuestions());
        assertEquals(given.multipleChoiceQuestions()
                .get(0).question(), actual.playMultipleChoiceQuestions()
                .get(0).question());
        assertThat(actual.playMultipleChoiceQuestions().get(0).answers(), containsInAnyOrder("1", "2", "3", "ta"));
    }

    @Test
    void addAuthorToMultipleChoiceQuiz() {
        //GIVEN
        MultipleChoiceQuiz given = new MultipleChoiceQuiz(null, null, "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1","2","3"), "ta")));
        when(authService.getAuthor()).thenReturn("author");

        //WHEN
        MultipleChoiceQuiz actual = mcms.addAuthorToMultipleChoiceQuiz(given);

        //THEN
        MultipleChoiceQuiz expected = new MultipleChoiceQuiz(null, "author", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1","2","3"), "ta")));

        assertEquals(expected,actual);
    }
}