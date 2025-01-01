package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends AuthException {
    public UnauthorizedAccessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
