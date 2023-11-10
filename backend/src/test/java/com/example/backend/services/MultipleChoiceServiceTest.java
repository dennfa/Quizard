package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuestion;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
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
    AuthenticationService authService = mock(AuthenticationService.class);
    MultipleChoiceService multipleChoiceService = new MultipleChoiceService(multipleChoiceRepo,mcms,authService);

    private MultipleChoiceQuiz setUp() {
        return new MultipleChoiceQuiz("1", "author", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1","2","3"), "ta")));
    }

    private MultipleChoiceQuiz setUpNoId() {
        return new MultipleChoiceQuiz(null, "author","quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1","2","3"), "ta")));
    }

    @Test
    void addQuiz() {

        //GIVEN
        when(multipleChoiceRepo.save(setUpNoId())).thenReturn(setUp());
        when(mcms.addAuthorToMultipleChoiceQuiz(setUpNoId())).thenReturn(setUpNoId());

        //When
        MultipleChoiceQuiz actual = multipleChoiceService.addQuiz(setUpNoId());

        //THEN
        MultipleChoiceQuiz expected = setUp();

        verify(multipleChoiceRepo).save(setUpNoId());
        verify(mcms).addAuthorToMultipleChoiceQuiz(setUpNoId());
        assertEquals(expected, actual);
    }

    @Test
    void getAllQuizzes() {
        //GIVEN
        MultipleChoiceQuiz mcq1 = setUp();
        MultipleChoiceQuiz mcq2 = setUpNoId();

        when(multipleChoiceRepo.findAll()).thenReturn(List.of(mcq1,mcq2));
        when(authService.getAuthor()).thenReturn("author");


        //WHEN
        List<MultipleChoiceQuiz> actual = multipleChoiceService.getAllQuizzes();
        //THEN
        List<MultipleChoiceQuiz> expected = List.of(mcq1,mcq2);

        verify(multipleChoiceRepo).findAll();
        verify(authService,times(2)).getAuthor();
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

    @Test
    void getAllPlayQuizzes() {
        //GIVEN
        MultipleChoiceQuiz mcq1 = setUp();
        MultipleChoiceQuiz mcq2 = setUpNoId();
        PlayMultipleChoiceQuiz pmcq1 = new PlayMultipleChoiceQuiz(
                mcq1.id(),mcq1.author(),mcq1.name(),mcq1.numberOfQuestions(),
                List.of(new PlayMultipleChoiceQuestion("q",List.of("1","2","3","ta"))));
        PlayMultipleChoiceQuiz pmcq2 = new PlayMultipleChoiceQuiz(
                mcq2.id(),mcq2.author(),mcq2.name(),mcq2.numberOfQuestions(),
                List.of(new PlayMultipleChoiceQuestion("q",List.of("1","2","3","ta"))));

        when(multipleChoiceRepo.findAll()).thenReturn(List.of(mcq1,mcq2));
        when(mcms.mapMultipleChoiceQuizToPlayMcq(mcq1)).thenReturn(pmcq1);
        when(mcms.mapMultipleChoiceQuizToPlayMcq(mcq2)).thenReturn(pmcq2);

        //WHEN
        List<PlayMultipleChoiceQuiz> actual = multipleChoiceService.getAllPlayQuizzes();
        //THEN
        List<PlayMultipleChoiceQuiz> expected = List.of(pmcq1,pmcq2);

        verify(multipleChoiceRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getPlayQuizById() {
        //GIVEN
        MultipleChoiceQuiz mcq = setUp();
        PlayMultipleChoiceQuiz expected = new PlayMultipleChoiceQuiz(
                mcq.id(),mcq.author(),mcq.name(),mcq.numberOfQuestions(),
                List.of(new PlayMultipleChoiceQuestion("q",List.of("1","2","3","ta"))));

        when(multipleChoiceRepo.findById("1")).thenReturn(Optional.of(mcq));
        when(mcms.mapMultipleChoiceQuizToPlayMcq(mcq)).thenReturn(expected);
        //WHEN
        PlayMultipleChoiceQuiz actual = multipleChoiceService.getPlayQuizById(setUp().id());
        //THEN

        verify(multipleChoiceRepo).findById(setUp().id());
        assertEquals(expected, actual);
    }

    @Test
    void getCorrectAnswer() {
        //GIVEN
        MultipleChoiceQuiz mcq = setUp();

        when(multipleChoiceRepo.findById("1")).thenReturn(Optional.of(mcq));
        //WHEN
        String actual = multipleChoiceService.getCorrectAnswer("1","0");
        //THEN

        verify(multipleChoiceRepo).findById("1");
        assertEquals("ta", actual);
    }
}