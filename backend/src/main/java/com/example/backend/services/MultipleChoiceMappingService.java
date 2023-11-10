package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.play.PlayMultipleChoiceQuestion;
import com.example.backend.models.play.PlayMultipleChoiceQuiz;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class MultipleChoiceMappingService {

    public PlayMultipleChoiceQuiz mapMultipleChoiceQuizToPlayMcq(MultipleChoiceQuiz mcq) {

        List<PlayMultipleChoiceQuestion> pmcqList = mcq.multipleChoiceQuestions()
                .stream().map(mcQuestion -> {
                            List<String> answers = new ArrayList<>();
                            answers.add(mcQuestion.trueAnswer());
                            answers.addAll(mcQuestion.falseAnswers());
                            Collections.shuffle(answers);

                            return PlayMultipleChoiceQuestion.builder()
                                    .question(mcQuestion.question())
                                    .answers(answers)
                                    .build();
                        }
                ).toList();

        return PlayMultipleChoiceQuiz.builder()
                .id(mcq.id())
                .name(mcq.name())
                .numberOfQuestions(mcq.numberOfQuestions())
                .playMultipleChoiceQuestions(pmcqList)
                .build();
    }

    public String getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .filter(auth -> auth.getPrincipal() instanceof OAuth2User)
                .map(auth -> (OAuth2User) auth.getPrincipal())
                .map(oauth2User -> (String) oauth2User.getAttribute("login"))
                .orElseThrow(() -> new NoSuchElementException("Could not get login from OAuth2User"));
    }

    public MultipleChoiceQuiz addAuthorToMultipleChoiceQuiz(MultipleChoiceQuiz mcq) {
        return MultipleChoiceQuiz.builder()
                .author(getAuthor())
                .name(mcq.name())
                .numberOfQuestions(mcq.numberOfQuestions())
                .multipleChoiceQuestions(mcq.multipleChoiceQuestions())
                .build();
    }
}
