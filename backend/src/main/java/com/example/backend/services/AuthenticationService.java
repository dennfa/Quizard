package com.example.backend.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthenticationService {

    public String getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .filter(auth -> auth.getPrincipal() instanceof OAuth2User)
                .map(auth -> (OAuth2User) auth.getPrincipal())
                .map(oauth2User -> (String) oauth2User.getAttribute("login"))
                .orElseThrow(() -> new NoSuchElementException("Could not get login from anonymous User"));
    }
}
