package com.qring.auth.presentation.v1.controller;

import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.v1.service.AuthService;
import com.qring.auth.infrastructure.jwt.JwtUtil;
import com.qring.auth.presentation.v1.req.PostAuthReqDTOV1;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthControllerV1 {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResDTO<Object>> loginBy(HttpServletResponse res, @Valid @RequestBody PostAuthReqDTOV1 dto) {

        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, authService.loginBy(dto));

        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(HttpStatus.OK.value())
                        .message("로그인에 성공하였습니다.")
                        .build(),
                HttpStatus.OK
        );
    }
}
