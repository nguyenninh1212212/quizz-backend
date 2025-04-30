package com.example.demo.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionHandle extends RuntimeException {
    private final Status status;
    private String message;

    public ExceptionHandle(Status status, Object... args) {
        super(status.getFormattedMessage(args));
        this.status = status;
        this.message = super.getMessage();
    }

}
