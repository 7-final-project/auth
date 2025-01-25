package com.qring.auth.application.global.exception;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends AuthException {
    public DuplicateResourceException(String message) {
        super(ErrorCode.DUPLICATE_ERROR, message);
    }
}
