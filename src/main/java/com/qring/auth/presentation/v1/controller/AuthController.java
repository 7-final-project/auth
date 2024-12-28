package com.qring.auth.presentation.v1.controller;

import com.qring.auth.application.v1.res.AuthPostResDTOv1;
import com.qring.auth.application.v1.res.ResDTO;
import com.qring.auth.infrastructure.docs.AuthControllerSwagger;
import com.qring.auth.presentation.v1.req.PostAuthReqDTOv1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthControllerSwagger {

    @PostMapping("/v1/auth")
    public ResponseEntity<ResDTO<AuthPostResDTOv1>> joinBy(@RequestBody PostAuthReqDTOv1 dto) {

        return new ResponseEntity<>(
                ResDTO.<AuthPostResDTOv1>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("회원가입에 성공했습니다.")
                        .data(AuthPostResDTOv1.of("tempUser", "tempRole", "temp0000@slack.com"))
                        .build(),
                HttpStatus.CREATED
        );
    }
}
