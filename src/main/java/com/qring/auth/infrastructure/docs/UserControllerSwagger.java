package com.qring.auth.infrastructure.docs;

import com.qring.auth.application.res.UserGetByIdResDTOV1;
import com.qring.auth.application.res.UserPostResDTOV1;
import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.presentation.req.PostUserReqDTOV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "회원가입, 수정, 삭제 관련 사용자 API")
@RequestMapping("/v1/users")
public interface UserControllerSwagger {

    @Operation(summary = "회원가입", description = "회원가입을 하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "회원가입 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/join")
    ResponseEntity<ResDTO<UserPostResDTOV1>> joinBy(@RequestBody PostUserReqDTOV1 dto);


    @Operation(summary = "회원 상세조회", description = "회원 상세조회를 하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 상세조회 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "회원 상세조회 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<ResDTO<UserGetByIdResDTOV1>> getBy(@PathVariable Long id);
}
