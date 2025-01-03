package com.qring.auth.domain.repository;

import com.qring.auth.domain.model.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByIdAndDeletedAtIsNull(Long id);

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    Optional<UserEntity> findFirstByUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(String username, String phone, String slackEmail);

    Optional<UserEntity> findUserByIdNotAndUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(Long id, String username, String phone, String slackEmail);

    UserEntity save(UserEntity userEntity);

}
