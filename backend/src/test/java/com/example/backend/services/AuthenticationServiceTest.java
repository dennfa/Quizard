package com.example.backend.services;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    Authentication authentication = mock(Authentication.class);
    OAuth2User oauth2User = mock(OAuth2User.class);

    AuthenticationService authService = new AuthenticationService();

    @Test
    void getAuthor() {
        //GIVEN
        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(oauth2User.getAttribute("login")).thenReturn("user");
        when(authentication.getPrincipal()).thenReturn(oauth2User);

        //WHEN
        String actual = authService.getAuthor();

        //THEN
        assertEquals("user", actual);
    }
}