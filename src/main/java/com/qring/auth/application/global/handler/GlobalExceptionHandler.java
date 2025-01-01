package com.qring.auth.application.global.handler;

import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.global.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ResDTO<Object>> authExceptionHandler(AuthException ex) {
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getMessage())
                        .build(),
                ex.getErrorCode().getHttpStatus()
        );
    }
}
