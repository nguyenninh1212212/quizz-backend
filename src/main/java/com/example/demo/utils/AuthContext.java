package com.example.demo.utils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.exceptions.ExceptionHandle;
import com.example.demo.exceptions.Status;
import com.example.demo.model.entity.Auth;
import com.example.demo.repository.AuthRepo;
import com.example.demo.repository.specification.AuthSpeci;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthContext {
    private final AuthRepo authRepo;

    public Auth auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new ExceptionHandle(Status.FORBIDDEN, "User is not authenticated");
        }

        Specification<Auth> spec = Specification.where(AuthSpeci.hasUsername(authentication.getName()));
        Auth auth = authRepo.findOne(spec)
                .orElseThrow(() -> new ExceptionHandle(Status.FORBIDDEN, "Auth details not found for user"));

        return auth;
    }
}
