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
    public void register(SignUpRequest dto) {

        // 1. 아이디 중복 확인
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_USERNAME);
        }

        // 2. 닉네임 중복 확인
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_NICKNAME);
        }

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 4. 엔티티 변환
        String imageUrl = "http://localhost:8080/images/default-profile.png";
        User user = User.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .name(dto.getName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .phonenumber(dto.getPhonenumber())
                .role("USER")              // 디폴트 역할
                .profileImageUrl(imageUrl) // 디폴트 이미지
                .build();

        // 5. 저장
        userRepository.insertUser(user);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
