package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.dto.request.PasswordRequest;
import com.ssafy.meongnyang.api.user.dto.request.SignUpRequest;
import com.ssafy.meongnyang.api.user.dto.request.UserUpdateRequest;
import com.ssafy.meongnyang.api.user.dto.response.UserResponse;
import com.ssafy.meongnyang.api.user.repository.UserRepository;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
                .passwordUpdatedAt(LocalDate.now()) // 디폴트 패스워드 업데이트 시간
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

    @Override
    public UserResponse getMyInfo(String username) {
        return userRepository.selectUserByUsername(username);
    }

    @Override
    public void changePassword(String username, PasswordRequest passwordRequest) {
        // 1. 새 비밀번호와 확인 비밀번호가 일치하는지 비교
        if(!passwordRequest.newPassword().equals(passwordRequest.confirmPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_CONFIRM_NOT_MATCH);
        }
        // 2. 입력한 비밀번호가 현재 비밀번호와 일치하는지 검증
        User user = userRepository.findByUsername(username);
        if (!passwordEncoder.matches(passwordRequest.currentPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        // 3. 비밀번호 암호화
        String encodedNewPassword = passwordEncoder.encode(passwordRequest.newPassword());
        
        // 비밀번호 업데이트
        int result = userRepository.updatePassword(username, encodedNewPassword);
    }
    private static final String DEFAULT_PROFILE_IMAGE_URL = "http://localhost:8080/images/default-profile.png";
    @Override
    public UserResponse updateMyInfo(String username, UserUpdateRequest request) {
        User user = userRepository.findByUsername(username);
        if (user == null) {throw new CustomException(ErrorCode.USER_NOT_FOUND);}

        user.setNickname(request.nickname());
        user.setEmail(request.email());
        user.setPhonenumber(request.phonenumber());


        // 프로필 이미지가 null이면 기본 이미지로 설정
        if (request.profileImageUrl() == null || request.profileImageUrl().isBlank()) {
            user.setProfileImageUrl(DEFAULT_PROFILE_IMAGE_URL);
        } else {
            user.setProfileImageUrl(request.profileImageUrl());
        }

        userRepository.updateUser(user);
        return new UserResponse(
                user.getNickname(),
                user.getEmail(),
                user.getPhonenumber(),
                user.getProfileImageUrl(),
                user.getPasswordUpdatedAt()
        );
    }

    @Override
    public void deleteMyAccount(String username) {

        // 1. 유저 조회
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        Long userId = user.getId();

        // 2. 자식 테이블부터 삭제 TODO : 주석 해제필요
//        petRepository.deleteByUserId(userId);         // 반려동물 삭제
//        dietRepository.deleteByUserId(userId);        // 식단 기록 삭제
//        commentRepository.deleteByUserId(userId);     // 댓글 삭제
//        postRepository.deleteByUserId(userId);        // 게시글 삭제

        // 3. 유저 삭제
        userRepository.deleteUser(username);
    }

}
