package com.qring.auth.infrastructure.repository;

import com.qring.auth.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByIdAndDeletedAtIsNull(Long id);

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    Optional<UserEntity> findFirstByUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(String username, String phone, String slackEmail);

    @Query("select u " +
            "from UserEntity u " +
            "where u.id <> :id " +
            "and (u.username = :username or u.phone = :phone or u.slackEmail = :slackEmail) " +
            "and u.deletedAt is null"
    )
    Optional<UserEntity> findUserByIdNotAndUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(Long id, String username, String phone, String slackEmail);

}
