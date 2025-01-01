package com.qring.auth.application.v1.service;

import com.qring.auth.domain.model.UserEntity;
import com.qring.auth.domain.repository.UserRepository;
import com.qring.auth.infrastructure.jwt.JwtUtil;
import com.qring.auth.presentation.v1.req.PostAuthReqDTOV1;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceV1 {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String loginBy(PostAuthReqDTOV1 dto) {

        UserEntity userEntityForCheck = getUserEntityByUsername(dto.getUser().getUsername());

        validatePasswordMatches(dto, userEntityForCheck);

        return JwtUtil.createToken(
                userEntityForCheck.getId(),
                userEntityForCheck.getUsername(),
                userEntityForCheck.getRole()
        );
    }

    // -----
    // NOTE : 아이디 검증 및 객체 조회
    private UserEntity getUserEntityByUsername(String username) {
        return userRepository.findByUsernameAndDeletedAtIsNull(username)
                .orElseThrow(() -> new IllegalArgumentException("아이디를 정확히 입력해주세요."));
    }

    // -----
    // NOTE : 비밀번호 검증
    private void validatePasswordMatches(PostAuthReqDTOV1 dto, UserEntity userEntityForCheck) {
        if (!passwordEncoder.matches(dto.getUser().getPassword(), userEntityForCheck.getPassword())) {
            throw new IllegalArgumentException("비밀번호를 정확히 입력해주세요.");
        }
    }
}
