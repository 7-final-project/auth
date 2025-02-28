package com.qring.auth.application.service;

import com.qring.auth.application.global.exception.DuplicateResourceException;
import com.qring.auth.application.global.exception.EntityNotFoundException;
import com.qring.auth.application.global.exception.UnauthorizedAccessException;
import com.qring.auth.application.res.UserGetByIdResDTOV1;
import com.qring.auth.application.res.UserPostResDTOV1;
import com.qring.auth.application.message.RedisMessagePublisherV1;
import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import com.qring.auth.infrastructure.util.PassportUtil;
import com.qring.auth.presentation.req.PostUserReqDTOV1;
import com.qring.auth.presentation.req.PutUserReqDTOV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceV1 {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisMessagePublisherV1 redisMessagePublisherV1;

    @Transactional
    public UserPostResDTOV1 joinBy(PostUserReqDTOV1 dto) {

        validateUserCreationProcess(dto);

        UserEntity userEntityForSave = UserEntity.createUserEntity(
                dto.getUser().getUsername(),
                passwordEncoder.encode(dto.getUser().getPassword()),
                dto.getUser().getPhone(),
                dto.getUser().getRole(),
                dto.getUser().getSlackEmail()
        );

        return UserPostResDTOV1.of(userRepository.save(userEntityForSave));
    }

    @Transactional(readOnly = true)
    public UserGetByIdResDTOV1 getBy(Long id) {

        UserEntity userEntityForMapping = getUserEntityById(id);

        return UserGetByIdResDTOV1.of(userEntityForMapping);
    }

    @Transactional
    public void putBy(String passport, Long id, PutUserReqDTOV1 dto) {

        validateUserModificationProcess(passport, id, dto);

        UserEntity userEntityForModify = getUserEntityById(id);

        if (!passwordEncoder.matches(dto.getUser().getPassword(), userEntityForModify.getPassword())) {
            userEntityForModify.modifyUserEntity(
                    dto.getUser().getUsername(),
                    passwordEncoder.encode(dto.getUser().getPassword()),
                    dto.getUser().getPhone(),
                    dto.getUser().getRole(),
                    dto.getUser().getSlackEmail(),
                    PassportUtil.getUsername(passport)
            );
        } else {
            userEntityForModify.modifyUserEntity(
                    dto.getUser().getUsername(),
                    userEntityForModify.getPassword(),
                    dto.getUser().getPhone(),
                    dto.getUser().getRole(),
                    dto.getUser().getSlackEmail(),
                    PassportUtil.getUsername(passport)
            );
        }

        redisMessagePublisherV1.publishUserModificationEvent(userEntityForModify.getId());
    }

    // -----
    // NOTE : 유저 조회 메서드
    private UserEntity getUserEntityById(Long id) {
        return userRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
    }

    // -----
    // NOTE : 회원가입 검증 프로세스
    private void validateUserCreationProcess(PostUserReqDTOV1 dto) {

        // NOTE : 필드 중복 검증
        userRepository.findFirstByUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(
                dto.getUser().getUsername(),
                dto.getUser().getPhone(),
                dto.getUser().getSlackEmail()
        ).ifPresent(existingUser -> {
            validateUsernameDuplication(existingUser, dto.getUser().getUsername());
            validatePhoneDuplication(existingUser, dto.getUser().getPhone());
            validateSlackEmailDuplication(existingUser, dto.getUser().getSlackEmail());
        });

    }

    // -----
    // NOTE : 회원수정 검증 프로세스
    private void validateUserModificationProcess(String passport, Long id, PutUserReqDTOV1 dto) {

        // NOTE : 권한 검증
        validateUserRole(passport);

        // NOTE : 필드 중복 검증
        validateUserDuplicationOnModify(id, dto);

    }

    // -----
    // NOTE : 관리자 권한 검증
    private static void validateUserRole(String passport) {
        if (!Objects.equals(PassportUtil.getRole(passport), "관리자")) {
            throw new UnauthorizedAccessException("접근 권한이 없습니다.");
        }
    }

    // -----
    // NOTE : 사용자 수정 간 중복 필드 검증
    private void validateUserDuplicationOnModify(Long id, PutUserReqDTOV1 dto) {
        userRepository.findUserByIdNotAndUsernameOrPhoneOrSlackEmailAndDeletedAtIsNull(
                id,
                dto.getUser().getUsername(),
                dto.getUser().getPhone(),
                dto.getUser().getSlackEmail()
        ).ifPresent(existingUser -> {
            validateUsernameDuplication(existingUser, dto.getUser().getUsername());
            validatePhoneDuplication(existingUser, dto.getUser().getPhone());
            validateSlackEmailDuplication(existingUser, dto.getUser().getSlackEmail());
        });
    }

    // -----
    // NOTE : 유저 이름 중복 검증
    private void validateUsernameDuplication(UserEntity userEntity, String username) {
        if (Objects.equals(userEntity.getUsername(), username)) {
            throw new DuplicateResourceException("이미 존재하는 유저이름입니다.");
        }
    }

    // -----
    // NOTE : 휴대폰 번호 중복 검증
    private void validatePhoneDuplication(UserEntity userEntity, String phone) {
        if (Objects.equals(userEntity.getPhone(), phone)) {
            throw new DuplicateResourceException("이미 등록된 휴대전화 번호입니다.");
        }
    }

    // -----
    // NOTE : 슬랙 이메일 중복 검증
    private void validateSlackEmailDuplication(UserEntity userEntity, String slackEmail) {
        if (Objects.equals(userEntity.getSlackEmail(), slackEmail)) {
            throw new DuplicateResourceException("이미 등록된 슬랙 이메일입니다.");
        }
    }
}
