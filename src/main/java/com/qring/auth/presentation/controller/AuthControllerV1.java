package com.qring.auth.presentation.controller;

import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.service.AuthServiceV1;
import com.qring.auth.infrastructure.docs.AuthControllerSwagger;
import com.qring.auth.infrastructure.jwt.JwtUtil;
import com.qring.auth.presentation.req.PostAuthReqDTOV1;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthControllerV1 implements AuthControllerSwagger {

    private final AuthServiceV1 authServiceV1;

    @PostMapping("/login")
    public ResponseEntity<ResDTO<Object>> loginBy(HttpServletResponse res, @Valid @RequestBody PostAuthReqDTOV1 dto) {

        res.addHeader(JwtUtil.AUTHORIZATION_HEADER, authServiceV1.loginBy(dto));

        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(HttpStatus.OK.value())
                        .message("로그인에 성공하였습니다.")
                        .build(),
                HttpStatus.OK
        );
    }

    // --
    // XXX : 이거는 External 패키지로 빼서 관리를 해야할까, 클라이언트 컨트롤러에 위치해야 할까 ?
    @PostMapping("/passport")
    public ResponseEntity<String> postBy(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(authServiceV1.postBy(userId));
    }
}
