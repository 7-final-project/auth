package com.qring.auth.presentation.v1.controller;

import com.qring.auth.application.v1.res.UserPostResDTOV1;
import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.v1.service.UserServiceV1;
import com.qring.auth.infrastructure.docs.UserControllerSwagger;
import com.qring.auth.presentation.v1.req.PostUserReqDTOV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserControllerV1 implements UserControllerSwagger {

    private final UserServiceV1 userServiceV1;

    @PostMapping("/join")
    public ResponseEntity<ResDTO<UserPostResDTOV1>> joinBy(@Valid @RequestBody PostUserReqDTOV1 dto) {

        return new ResponseEntity<>(
                ResDTO.<UserPostResDTOV1>builder()
                        .code(HttpStatus.CREATED.value())
                        .message("회원가입에 성공했습니다.")
                        .data(userServiceV1.joinBy(dto))
                        .build(),
                HttpStatus.CREATED
        );
    }
}
