package com.example.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MultipleChoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;
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
}