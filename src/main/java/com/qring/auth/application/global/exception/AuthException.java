package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public AuthException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
