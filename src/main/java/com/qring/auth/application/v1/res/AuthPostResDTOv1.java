package com.qring.auth.application.v1.res;

import com.qring.auth.domain.model.UserEntity;
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

    private static AuthPostResDTOv1 of(UserEntity userEntity) {
        return AuthPostResDTOv1.builder()
                .user(User.from(userEntity))
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

        public static User from(UserEntity userEntity) {
            return User.builder()
                    .username(userEntity.getUsername())
                    .role(userEntity.getRole().getAuthority())
                    .slackEmail(userEntity.getSlackEmail())
                    .build();
        }
    }
}
