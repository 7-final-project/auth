package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class ValidationException extends AuthException {
    public ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
