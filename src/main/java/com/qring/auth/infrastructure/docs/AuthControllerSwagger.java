package com.qring.auth.infrastructure.docs;

import com.qring.auth.application.global.dto.ResDTO;
import com.qring.auth.presentation.req.PostAuthReqDTOV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Auth", description = "로그인 인증 API")
@RequestMapping("/v1/auth")
public interface AuthControllerSwagger {

    @Operation(summary = "로그인", description = "로그인을 하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(schema = @Schema(implementation = ResDTO.class))),
            @ApiResponse(responseCode = "400", description = "로그인 실패.", content = @Content(schema = @Schema(implementation = ResDTO.class)))
    })
    @PostMapping("/login")
    ResponseEntity<ResDTO<Object>> loginBy(HttpServletResponse res, @Valid @RequestBody PostAuthReqDTOV1 dto);
}
