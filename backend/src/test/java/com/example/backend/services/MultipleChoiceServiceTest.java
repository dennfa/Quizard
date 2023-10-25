package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.MultipleChoiceQuizResponse;
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

    private MultipleChoiceQuizResponse setUp2() {
        return new MultipleChoiceQuizResponse("1", "quiz", List.of(
                new MultipleChoiceQuestion("q", "fa", "ta")));
    }

    @Test
    void addQuiz() {

        //GIVEN
        NewMultipleChoiceQuiz newMultipleChoiceQuiz = new NewMultipleChoiceQuiz("q",
                List.of(new MultipleChoiceQuestion("q", "fa", "ta")));

        when(multipleChoiceRepo.save(setUp())).thenReturn(setUp());
        when(multipleChoiceMappingService.mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz)).thenReturn(setUp());
        when(multipleChoiceMappingService.mapMcQuizToMcQuizResponse(setUp()))
                .thenReturn(setUp2());

        //When
        MultipleChoiceQuizResponse actual = multipleChoiceService.addQuiz(newMultipleChoiceQuiz);

        //THEN
        MultipleChoiceQuizResponse expected = setUp2();

        verify(multipleChoiceRepo).save(setUp());
        verify(multipleChoiceMappingService).mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz);
        verify(multipleChoiceMappingService).mapMcQuizToMcQuizResponse(setUp());
        assertEquals(expected, actual);
    }
}