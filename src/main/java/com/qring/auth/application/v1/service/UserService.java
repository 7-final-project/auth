package com.qring.auth.application.v1.service;

import com.qring.auth.application.v1.res.UserPostResDTOv1;
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
    public UserPostResDTOv1 joinBy(PostUserReqDTOV1 dto) {

        validateUsernameDuplication(dto.getUser().getUsername());

        UserEntity userEntityForSave = UserEntity.createUserEntity(
                dto.getUser().getUsername(),
                passwordEncoder.encode(dto.getUser().getPassword()),
                dto.getUser().getPhone(),
                dto.getUser().getRole(),
                dto.getUser().getSlackEmail()
        );

        return UserPostResDTOv1.of(userRepository.save(userEntityForSave));
    }

    private void validateUsernameDuplication(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 유저이름입니다.");
        }
    }
}
