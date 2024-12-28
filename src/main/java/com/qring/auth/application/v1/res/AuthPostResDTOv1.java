package com.qring.auth.application.v1.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthPostResDTOv1 {

    private User user;

    public static AuthPostResDTOv1 of(String username, String role, String slackEmail) {
        return AuthPostResDTOv1.builder()
                .user(User.from(username, role, slackEmail))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {

        private String username;
        private String role;
        private String slackEmail;

        public static User from(String username, String role, String slackEmail) {
            return User.builder()
                    .username(username)
                    .role(role)
                    .slackEmail(slackEmail)
                    .build();
        }
    }
}
