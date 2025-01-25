package com.qring.auth.presentation.controller;

import com.qring.auth.application.res.UserGetByIdResDTOV1;
import com.qring.auth.application.res.UserPostResDTOV1;
import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.application.service.UserServiceV1;
import com.qring.auth.infrastructure.docs.UserControllerSwagger;
import com.qring.auth.presentation.req.PostUserReqDTOV1;
import com.qring.auth.presentation.req.PutUserReqDTOV1;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResDTO<UserGetByIdResDTOV1>> getBy(@PathVariable Long id) {

        return new ResponseEntity<>(
                ResDTO.<UserGetByIdResDTOV1>builder()
                        .code(HttpStatus.OK.value())
                        .message("회원 상세조회에 성공했습니다.")
                        .data(userServiceV1.getBy(id))
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResDTO<Object>> putBy(@RequestHeader("X-Passport-Token") String passport,
                                                @PathVariable Long id,
                                                @Valid @RequestBody PutUserReqDTOV1 dto) {

        userServiceV1.putBy(passport, id, dto);

        return new ResponseEntity<>(
                ResDTO.builder()
                        .code(HttpStatus.OK.value())
                        .message("회원 수정에 성공했습니다.")
                        .build(),
                HttpStatus.OK
        );
    }
}
