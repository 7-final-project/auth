package com.qring.auth.application.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, 2002),
    AUTHORITY_ERROR(HttpStatus.FORBIDDEN, 2001),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, 2003);

    private final HttpStatus httpStatus;
    private final Integer code;
}
