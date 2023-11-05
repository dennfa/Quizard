package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MultipleChoiceServiceTest {

    MultipleChoiceRepo multipleChoiceRepo = mock(MultipleChoiceRepo.class);
    MultipleChoiceMappingService mcms = mock(MultipleChoiceMappingService.class);
    MultipleChoiceService multipleChoiceService = new MultipleChoiceService(multipleChoiceRepo,mcms);

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

    @Test
    void getAllQuizzes() {
        //GIVEN
        MultipleChoiceQuiz mcq1 = setUp();
        MultipleChoiceQuiz mcq2 = setUpNoId();

        when(multipleChoiceRepo.findAll()).thenReturn(List.of(mcq1,mcq2));

        //WHEN
        List<MultipleChoiceQuiz> actual = multipleChoiceService.getAllQuizzes();
        //THEN
        List<MultipleChoiceQuiz> expected = List.of(mcq1,mcq2);

        verify(multipleChoiceRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getQuizById() {
        //GIVEN
        MultipleChoiceQuiz expected = setUp();

        when(multipleChoiceRepo.findById("1")).thenReturn(Optional.of(setUp()));
        //WHEN
        MultipleChoiceQuiz actual = multipleChoiceService.getQuizById(setUp().id());
        //THEN

        verify(multipleChoiceRepo).findById(setUp().id());
        assertEquals(expected, actual);
    }

    @Test
    void updateQuizIdExists() {
        //GIVEN
        MultipleChoiceQuiz origin = setUp();
        MultipleChoiceQuiz update = origin.withName("update");

        when(multipleChoiceRepo.findById("1")).thenReturn(Optional.of(setUp()));
        when(multipleChoiceRepo.save(update)).thenReturn(update);
        //WHEN

        MultipleChoiceQuiz actual = multipleChoiceService.updateQuiz(update);
        //THEN
        verify(multipleChoiceRepo).findById("1");
        verify(multipleChoiceRepo).save(update);
        assertEquals(update,actual);
    }

    @Test
    void updateQuizIdNonExisting() {
        //GIVEN

        MultipleChoiceQuiz quiz = setUp();

        when(multipleChoiceRepo.findById("1")).thenReturn(Optional.empty());
        when(multipleChoiceRepo.save(setUp())).thenReturn(setUp());
        //WHEN

        try{
            multipleChoiceService.updateQuiz(quiz);
        } catch(NoSuchElementException noSuchElementException) {
            System.out.println("No Problem");
        }

        //THEN
        verify(multipleChoiceRepo).findById("1");
        verify(multipleChoiceRepo,times(0)).save(setUp());
        assertThrows(NoSuchElementException.class,()->multipleChoiceService.updateQuiz(quiz));
    }

    @Test
    void deleteQuiz() {
        //GIVEN

        //WHEN
        multipleChoiceService.deleteQuiz("1");
        //THEN

        verify(multipleChoiceRepo).deleteById("1");
    }
}