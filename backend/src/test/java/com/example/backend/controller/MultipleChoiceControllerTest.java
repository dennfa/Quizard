package com.example.backend.controller;

import com.example.backend.models.MultipleChoiceQuestion;
import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MultipleChoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MultipleChoiceRepo multipleChoiceRepo;


    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

    @Test
    @DirtiesContext
    void addQuiz() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "name": "name",
                                "numberOfQuestions": 10,
                                "multipleChoiceQuestions": [
                                {"question": "question",
                                "falseAnswers": ["1", "2", "3"],
                                "trueAnswer": "trueAnswer"
                                }]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                         {
                        "author": "user",
                        "name": "name",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswers": ["1", "2", "3"],
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DirtiesContext
    void getAllQuizzes() throws Exception {

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/update")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user"))))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         [{
                         "id": "1",
                         "author": "user",
                        "name": "quiz",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "q",
                        "falseAnswers": ["1", "2", "3"],
                        "trueAnswer": "ta"
                        }]
                        }]
                        """));
    }

    @Test
    @DirtiesContext
    void getQuizById() throws Exception {

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/update/1")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user"))))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "id": "1",
                         "author": "user",
                        "name": "quiz",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "q",
                        "falseAnswers": ["1", "2", "3"],
                        "trueAnswer": "ta"
                        }]
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void updateQuiz() throws Exception {

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/update")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id": "1",
                                "author": "user",
                                "name": "name",
                                "numberOfQuestions": 10,
                                "multipleChoiceQuestions": [
                                {"question": "question",
                                "falseAnswers": ["1","2","3"],
                                "trueAnswer": "trueAnswer"
                                }]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "id": "1",
                        "author": "user",
                        "name": "name",
                        "numberOfQuestions": 10,
                        "multipleChoiceQuestions": [
                        {"question": "question",
                        "falseAnswers": ["1","2","3"],
                        "trueAnswer": "trueAnswer"
                        }]
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void deleteQuiz() throws Exception {

        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/update/1")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user"))))
                .andExpect(status().isOk());

        Optional<MultipleChoiceQuiz> deletedQuiz = multipleChoiceRepo.findById("1");
        assertFalse(deletedQuiz.isPresent());
    }

    @Test
    @DirtiesContext
    void getCorrectAnswer() throws Exception {
        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/take/1")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user")))
                        .content("0"))
                .andExpect(status().isOk())
                .andExpect(content().string("ta"));
    }

    @Test
    void getAllPlayQuizzes() throws Exception {
        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/take")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user"))))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         [{
                         "id": "1",
                         "author": "user",
                        "name": "quiz",
                        "numberOfQuestions": 10,
                        "playMultipleChoiceQuestions": [
                        {"question": "q"
                        }]
                        }]
                        """))
                .andExpect(jsonPath("$[0].playMultipleChoiceQuestions[0].answers", containsInAnyOrder("1", "2", "3", "ta")));
    }

    @Test
    void getPlayQuizById() throws Exception {
        multipleChoiceRepo.save(new MultipleChoiceQuiz("1", "user", "quiz", 10, List.of(
                new MultipleChoiceQuestion("q", List.of("1", "2", "3"), "ta"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/take/1")
                        .with(oidcLogin().userInfoToken(token -> token.claim("login", "user"))))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                         {
                         "id": "1",
                         "author": "user",
                        "name": "quiz",
                        "numberOfQuestions": 10,
                        "playMultipleChoiceQuestions": [
                        {"question": "q"
                        }]
                        }
                        """))
                .andExpect(jsonPath("$.playMultipleChoiceQuestions[0].answers", containsInAnyOrder("1", "2", "3", "ta")));
    }
}