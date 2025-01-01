package com.qring.auth.application.v1.service;

import com.qring.auth.application.v1.res.UserPostResDTOV1;
import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import com.qring.auth.presentation.v1.req.PostUserReqDTOV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserPostResDTOV1 joinBy(PostUserReqDTOV1 dto) {

        // --
        // XXX : 데이터 중복 검증을 서비스 레이어에서 수행할까 ? DB 레벨에서 수행할까 ?
        // --
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

    // -----
    // NOTE : 회원가입 검증 프로세스
    private void validateUserCreationProcess(PostUserReqDTOV1 dto) {

        validateUsernameDuplication(dto.getUser().getUsername());

        validatePhoneDuplication(dto.getUser().getPhone());

        validateSlackEmailDuplication(dto.getUser().getSlackEmail());

    }

    // -----
    // NOTE : 휴대폰 번호 중복 검증
    private void validatePhoneDuplication(String phone) {
        if (userRepository.existsByPhoneAndDeletedAtIsNull(phone)) {
            throw new IllegalArgumentException("이미 등록된 휴대폰 번호입니다.");
        }
    }

    // -----
    // NOTE : 슬랙 이메일 중복 검증
    private void validateSlackEmailDuplication(String slackEmail) {
        if (userRepository.existsBySlackEmailAndDeletedAtIsNull(slackEmail)) {
            throw new IllegalArgumentException("이미 등록된 슬랙 이메일입니다.");
        }
    }

    // -----
    // NOTE : 유저 이름 중복 검증
    private void validateUsernameDuplication(String username) {
        if (userRepository.existsByUsernameAndDeletedAtIsNull(username)) {
            throw new IllegalArgumentException("이미 존재하는 유저이름입니다.");
        }
    }
}
