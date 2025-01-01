package com.qring.auth.application.global.handler;

import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.global.exception.DuplicateResourceException;
import com.qring.auth.application.global.exception.UnauthorizedAccessException;
import com.qring.auth.application.global.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnauthorizedAccessException.class})
    public ResponseEntity<ResDTO<Object>> unauthorizedAccessExceptionHandler(UnauthorizedAccessException ex) {
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getMessage())
                        .build(),
                ex.getErrorCode().getHttpStatus()
        );
    }

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<ResDTO<Object>> duplicateResourceExceptionHandler(DuplicateResourceException ex) {
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getMessage())
                        .build(),
                ex.getErrorCode().getHttpStatus()
        );
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ResDTO<Object>> validationExceptionHandler(ValidationException ex) {
        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(ex.getMessage())
                        .build(),
                ex.getErrorCode().getHttpStatus()
        );
    }
}
