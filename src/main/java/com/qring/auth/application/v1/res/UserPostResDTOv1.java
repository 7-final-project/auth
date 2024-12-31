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
public class UserPostResDTOv1 {

    private User user;

    public static UserPostResDTOv1 of(UserEntity userEntity) {
        return UserPostResDTOv1.builder()
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
