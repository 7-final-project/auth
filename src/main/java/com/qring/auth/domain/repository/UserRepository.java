package com.qring.auth.domain.repository;

import com.qring.auth.domain.model.UserEntity;

public interface UserRepository {

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsBySlackEmail(String slackEmail);

    UserEntity save(UserEntity userEntity);

}
