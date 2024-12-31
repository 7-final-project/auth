package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    public boolean existsByPhone(String phone) {
        return jpaUserRepository.existsByPhone(phone);
    }

    public boolean existsBySlackEmail(String slackEmail) {
        return jpaUserRepository.existsBySlackEmail(slackEmail);
    }

    public UserEntity save(UserEntity userEntity) {
        return jpaUserRepository.save(userEntity);
    }
}
