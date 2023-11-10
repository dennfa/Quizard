package com.example.backend.controller;

import com.example.backend.services.MultipleChoiceMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final MultipleChoiceMappingService mcms;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getUserName() {
        return mcms.getAuthor();
    }
}
