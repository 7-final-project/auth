package com.qring.auth.application.v1.service;

import com.qring.auth.application.global.exception.DuplicateResourceException;
import com.qring.auth.application.global.exception.EntityNotFoundException;
import com.qring.auth.application.v1.res.UserPostResDTOV1;
import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import com.qring.auth.presentation.v1.req.PostUserReqDTOV1;
import com.qring.auth.presentation.v1.req.PutUserReqDTOV1;
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

    @Transactional
    public void putBy(String passport, Long id, PutUserReqDTOV1 dto) {

        // --
        // TODO : 권한 검증 로직 구현 ( MASTER만 접근 가능 )
        // --

        validateUserModificationProcess(id, dto);

        UserEntity userEntityForModify = getUserEntityById(id);

        if (!passwordEncoder.matches(dto.getUser().getPassword(), userEntityForModify.getPassword())) {
            userEntityForModify.modifyUserEntity(
                    dto.getUser().getUsername(),
                    passwordEncoder.encode(dto.getUser().getPassword()),
                    dto.getUser().getPhone(),
                    dto.getUser().getRole(),
                    dto.getUser().getSlackEmail(),
                    "modifiedBy"
            );
        } else {
            userEntityForModify.modifyUserEntity(
                    dto.getUser().getUsername(),
                    userEntityForModify.getPassword(),
                    dto.getUser().getPhone(),
                    dto.getUser().getRole(),
                    dto.getUser().getSlackEmail(),
                    "modifiedBy"
            );
        }
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

        validateUsernameDuplication(dto.getUser().getUsername());

        validatePhoneDuplication(dto.getUser().getPhone());

        validateSlackEmailDuplication(dto.getUser().getSlackEmail());

    }

    // -----
    // NOTE : 회원수정 검증 프로세스
    private void validateUserModificationProcess(Long id, PutUserReqDTOV1 dto) {
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
    private void validateUsernameDuplication(String username) {
        if (userRepository.existsByUsernameAndDeletedAtIsNull(username)) {
            throw new DuplicateResourceException("이미 존재하는 유저이름입니다.");
        }
    }

    private void validateUsernameDuplication(UserEntity userEntity, String username) {
        if (Objects.equals(userEntity.getUsername(), username)) {
            throw new DuplicateResourceException("이미 존재하는 유저이름입니다.");
        }
    }

    // -----
    // NOTE : 휴대폰 번호 중복 검증
    private void validatePhoneDuplication(String phone) {
        if (userRepository.existsByPhoneAndDeletedAtIsNull(phone)) {
            throw new DuplicateResourceException("이미 등록된 휴대폰 번호입니다.");
        }
    }

    private void validatePhoneDuplication(UserEntity userEntity, String phone) {
        if (Objects.equals(userEntity.getPhone(), phone)) {
            throw new DuplicateResourceException("이미 등록된 휴대전화 번호입니다.");
        }
    }

    // -----
    // NOTE : 슬랙 이메일 중복 검증
    private void validateSlackEmailDuplication(String slackEmail) {
        if (userRepository.existsBySlackEmailAndDeletedAtIsNull(slackEmail)) {
            throw new DuplicateResourceException("이미 등록된 슬랙 이메일입니다.");
        }
    }

    private void validateSlackEmailDuplication(UserEntity userEntity, String slackEmail) {
        if (Objects.equals(userEntity.getSlackEmail(), slackEmail)) {
            throw new DuplicateResourceException("이미 등록된 슬랙 이메일입니다.");
        }
    }
}
