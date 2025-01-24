package com.qring.auth.application.res;

import com.qring.auth.domain.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostResDTOV1 {

    private User user;

    public static UserPostResDTOV1 of(UserEntity userEntity) {
        return UserPostResDTOV1.builder()
                .user(User.from(userEntity))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {

        private Long id;
        private String username;
        private String phone;
        private String role;
        private String slackEmail;

        public static User from(UserEntity userEntity) {
            return User.builder()
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .phone(userEntity.getPhone())
                    .role(userEntity.getRole().getAuthority())
                    .slackEmail(userEntity.getSlackEmail())
                    .build();
        }
    }
}
