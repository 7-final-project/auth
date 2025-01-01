package com.qring.auth.domain.repository;

import com.qring.auth.domain.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsBySlackEmail(String slackEmail);

    UserEntity save(UserEntity userEntity);

}
