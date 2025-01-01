package com.qring.auth.domain.repository;

import com.qring.auth.domain.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    boolean existsByUsernameAndDeletedAtIsNull(String username);

    boolean existsByPhoneAndDeletedAtIsNull(String phone);

    boolean existsBySlackEmailAndDeletedAtIsNull(String slackEmail);

    UserEntity save(UserEntity userEntity);

}
