package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public Optional<UserEntity> findByIdAndDeletedAtIsNull(Long id) {
        return jpaUserRepository.findByIdAndDeletedAtIsNull(id);
    }

    public Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username) {
        return jpaUserRepository.findByUsernameAndDeletedAtIsNull(username);
    }

    public Optional<UserEntity> findFirstByUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(String username, String phone, String slackEmail) {
        return jpaUserRepository.findFirstByUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(username, phone, slackEmail);
    }

    public Optional<UserEntity> findUserByIdNotAndUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(Long id, String username, String phone, String slackEmail) {
        return jpaUserRepository.findUserByIdNotAndUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(id, username, phone, slackEmail);
    }

    public UserEntity save(UserEntity userEntity) {
        return jpaUserRepository.save(userEntity);
    }
}
