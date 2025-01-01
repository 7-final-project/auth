package com.qring.auth.domain.model;

import com.qring.auth.domain.model.constraint.RoleType;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_user")
public class UserEntity {

    @Id @Tsid
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @Column(name = "slack_email", nullable = false)
    private String slackEmail;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at" , nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Builder
    public UserEntity (String username, String password, String phone, RoleType role, String slackEmail) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.slackEmail = slackEmail;
        this.createdBy = username;
        this.modifiedBy = username;
    }

    public static UserEntity createUserEntity(String username, String password, String phone, String role, String slackEmail) {
        return UserEntity.builder()
                .username(username)
                .password(password)
                .phone(phone)
                .role(RoleType.fromString(role))
                .slackEmail(slackEmail)
                .build();
    }
}
