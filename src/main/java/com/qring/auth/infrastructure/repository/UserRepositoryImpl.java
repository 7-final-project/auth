package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username) {
        return jpaUserRepository.findByUsernameAndDeletedAtIsNull(username);
    }

    public boolean existsByUsernameAndDeletedAtIsNull(String username) {
        return jpaUserRepository.existsByUsernameAndDeletedAtIsNull(username);
    }

    public boolean existsByPhoneAndDeletedAtIsNull(String phone) {
        return jpaUserRepository.existsByPhoneAndDeletedAtIsNull(phone);
    }

    public boolean existsBySlackEmailAndDeletedAtIsNull(String slackEmail) {
        return jpaUserRepository.existsBySlackEmailAndDeletedAtIsNull(slackEmail);
    }

    public UserEntity save(UserEntity userEntity) {
        return jpaUserRepository.save(userEntity);
    }
}
