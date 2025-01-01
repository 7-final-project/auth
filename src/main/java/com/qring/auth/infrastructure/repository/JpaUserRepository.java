package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    boolean existsByUsername(String username);

    boolean existsByPhone(String phone);

    boolean existsBySlackEmail(String email);

}
