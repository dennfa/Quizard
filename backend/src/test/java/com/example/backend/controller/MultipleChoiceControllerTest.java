package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MultipleChoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MultipleChoiceRepo multipleChoiceRepo;

    @Test
    @DirtiesContext
    void addQuiz() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "name",
                        "description": "description",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswer": "falseAnswer",
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                         {
                        "name": "name",
                        "description": "description",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswer": "falseAnswer",
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void getAllQuizzes() throws Exception{

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         [{
                        "name": "quiz",
                        "description": "d",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "q",
                        "falseAnswer": "fa",
                        "trueAnswer": "ta"
                        }]
                        }]
                        """));
    }

    @Test
    void getQuizById() throws Exception{

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/update/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "id": "1",
                        "name": "quiz",
                        "description": "d",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "q",
                        "falseAnswer": "fa",
                        "trueAnswer": "ta"
                        }]
                        }
                        """));
    }

    @Test
    void updateQuizBy() throws Exception{

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                        "id": "1",
                        "name": "name",
                        "description": "description",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswer": "falseAnswer",
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                        "name": "name",
                        "description": "description",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswer": "falseAnswer",
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """));
    }

    @Test
    void deleteQuiz() throws Exception{

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "quiz", "d", 10, List.of(
                new MultipleChoiceQuestion("q", "fa", "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/update/1"))
                .andExpect(status().isOk());

        Optional<MultipleChoiceQuiz> deletedQuiz = multipleChoiceRepo.findById("1");
        assertFalse(deletedQuiz.isPresent());
    }
}