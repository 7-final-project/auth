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
public class UserGetByIdResDTOV1 {

    private User user;

    public static UserGetByIdResDTOV1 of(UserEntity userEntity) {
        return UserGetByIdResDTOV1.builder()
                .user(User.from(userEntity))
                .build();
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {

        private Long id;
        private String username;
        private String phone;
        private String slackEmail;

        public static User from(UserEntity userEntity) {
            return User.builder()
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .phone(userEntity.getPhone())
                    .slackEmail(userEntity.getSlackEmail())
                    .build();
        }
    }
}
