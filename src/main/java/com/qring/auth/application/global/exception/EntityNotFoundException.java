package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends AuthException {
    public EntityNotFoundException(String message) {
        super(ErrorCode.NOT_FOUND_ERROR, message);
    }
}
