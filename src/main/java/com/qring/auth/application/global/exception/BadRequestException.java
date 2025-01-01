package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends AuthException {
    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST_ERROR, message);
    }
}
