package com.example.backend.services;

import com.example.backend.models.MultipleChoiceQuiz;
import com.example.backend.models.NewMultipleChoiceQuiz;
import com.example.backend.repos.MultipleChoiceRepo;
import com.example.backend.services.mappings.MultipleChoiceMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MultipleChoiceService {

    private final MultipleChoiceRepo multipleChoiceRepo;
    private final MultipleChoiceMappingService mcms;

    public MultipleChoiceQuiz addQuiz(NewMultipleChoiceQuiz newMultipleChoiceQuiz) {
        MultipleChoiceQuiz mcq = mcms.mapNewMCQuizToMCQuiz(newMultipleChoiceQuiz);
        return multipleChoiceRepo.save(mcq);

    }
}
