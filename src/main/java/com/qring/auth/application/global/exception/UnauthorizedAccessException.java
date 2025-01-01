package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends AuthException {
    public UnauthorizedAccessException(String message) {
        super(ErrorCode.AUTHORITY_ERROR, message);
    }
}
