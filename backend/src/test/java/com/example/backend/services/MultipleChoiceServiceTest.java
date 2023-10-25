package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.NewMultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import com.example.backend.services.mappings.MultipleChoiceMappingService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultipleChoiceServiceTest {

    MultipleChoiceRepo multipleChoiceRepo = mock(MultipleChoiceRepo.class);
    MultipleChoiceMappingService multipleChoiceMappingService = mock(MultipleChoiceMappingService.class);
    MultipleChoiceService multipleChoiceService = new MultipleChoiceService(multipleChoiceRepo,
            multipleChoiceMappingService);

    private MultipleChoiceQuiz setUp() {
        return new MultipleChoiceQuiz("1", "quiz", List.of(
                new MultipleChoiceQuestion("q", "fa", "ta")));
    }

    private MultipleChoiceQuiz setUpNoId() {
        return new MultipleChoiceQuiz(null, "quiz", List.of(
                new MultipleChoiceQuestion("q", "fa", "ta")));
    }

    @Test
    void addQuiz() {

        //GIVEN
        NewMultipleChoiceQuiz newMultipleChoiceQuiz = new NewMultipleChoiceQuiz("q",
                List.of(new MultipleChoiceQuestion("q", "fa", "ta")));

        when(multipleChoiceRepo.save(setUpNoId())).thenReturn(setUp());
        when(multipleChoiceMappingService.mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz)).thenReturn(setUpNoId());

        //When
        MultipleChoiceQuiz actual = multipleChoiceService.addQuiz(newMultipleChoiceQuiz);

        //THEN
        MultipleChoiceQuiz expected = setUp();

        verify(multipleChoiceRepo).save(setUpNoId());
        verify(multipleChoiceMappingService).mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz);
        assertEquals(expected, actual);
    }
}