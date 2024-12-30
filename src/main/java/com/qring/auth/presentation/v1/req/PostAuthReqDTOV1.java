package com.qring.auth.presentation.v1.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostAuthReqDTOV1 {

    @Valid
    @NotNull(message = "회원 정보를 입력해주세요.")
    private User user;

    @Getter
    public static class User {

        @NotBlank(message = "아이디를 입력해주세요.")
        private String username;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
                message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
        private String password;

        @NotBlank(message = "권한을 입력해주세요.")
        private String role;

        @NotBlank(message = "이메일을 입력해주세요.")
        private String slackEmail;
    }
}