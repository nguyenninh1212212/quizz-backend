package com.example.demo.exceptions;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    CREATED(HttpStatus.CREATED, "created success"),
    SUCCESS(HttpStatus.ACCEPTED, ""),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "{0} is invalid"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    ALREADY_EXISTS(HttpStatus.CONFLICT, "{0} already exists"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "You are not authorized"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "Access is forbidden"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token has expired"),
    FILE_EXPIRED(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE, "File too large"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "No {0}"),
    CONFLICT(HttpStatus.CONFLICT, " {0} is already exists"),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "Validation failed: {0}");

    private final HttpStatus httpStatus;
    private final String message;

    public String getFormattedMessage(Object... args) {
        String res = MessageFormat.format(this.message, args);
        return res;
    }

}
