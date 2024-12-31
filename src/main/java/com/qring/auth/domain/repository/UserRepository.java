package com.qring.auth.domain.repository;

import com.qring.auth.domain.model.UserEntity;

public interface UserRepository {

    boolean existsByUsername(String username);

    UserEntity save(UserEntity userEntity);

}
