package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

}
