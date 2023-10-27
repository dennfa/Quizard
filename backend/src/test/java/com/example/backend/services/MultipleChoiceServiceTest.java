package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultipleChoiceServiceTest {

    MultipleChoiceRepo multipleChoiceRepo = mock(MultipleChoiceRepo.class);
    MultipleChoiceService multipleChoiceService = new MultipleChoiceService(multipleChoiceRepo);

    private MultipleChoiceQuiz setUp() {
        return new MultipleChoiceQuiz("1", "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta")));
    }

    private MultipleChoiceQuiz setUpNoId() {
        return new MultipleChoiceQuiz(null, "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta")));
    }

    @Test
    void addQuiz() {

        //GIVEN
        MultipleChoiceQuiz multipleChoiceQuiz = setUpNoId();

        when(multipleChoiceRepo.save(setUpNoId())).thenReturn(setUp());

        //When
        MultipleChoiceQuiz actual = multipleChoiceService.addQuiz(multipleChoiceQuiz);

        //THEN
        MultipleChoiceQuiz expected = setUp();

        verify(multipleChoiceRepo).save(setUpNoId());
        assertEquals(expected, actual);
    }
}