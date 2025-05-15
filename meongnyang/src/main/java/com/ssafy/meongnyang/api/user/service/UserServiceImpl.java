package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.dto.SignUpRequest;
import com.ssafy.meongnyang.api.user.repository.UserRepository;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(SignUpRequest userdto) {
        // 1. 중복확인
        // 아이디 중복확인
        if(userRepository.existsById(userdto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_ID);
        }
        // 닉네임 중복확인
        if(userRepository.existsByNickName(userdto.getNickname())) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_NICKNAME);
        }
        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userdto.getPassword());
        userdto.setPassword(encodedPassword);

        // 3. 엔티티 변환
        User user = User.builder()
                .username(userdto.getUsername())
                .nickname(userdto.getNickname())
                .email(userdto.getEmail())
                .password(encodedPassword)
                .build();
        // 4. 저장
        userRepository.insertUser(user);
    }

}
