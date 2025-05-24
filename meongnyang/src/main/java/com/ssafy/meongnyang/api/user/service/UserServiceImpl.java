package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.pet.repository.PetRepository;
import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.dto.request.PasswordRequest;
import com.ssafy.meongnyang.api.user.dto.request.SignUpRequest;
import com.ssafy.meongnyang.api.user.dto.request.UserUpdateRequest;
import com.ssafy.meongnyang.api.user.dto.response.UserResponse;
import com.ssafy.meongnyang.api.user.repository.UserRepository;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.external.S3Service;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;

    private static final String DEFAULT_PROFILE_IMAGE_URL = "http://localhost:8080/images/default-profile.png";
    private static final String BUCKET_PATH = "https://meongnyang-ssafy.s3.ap-northeast-2.amazonaws.com/";
    private static final String DIET_FILE_PATH = "pet_file/";

    private String uploadImageToS3(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        return BUCKET_PATH + s3Service.uploadImage(DIET_FILE_PATH, file);
    }
    @Override
    public void register(SignUpRequest signUpRequest) {

        // 1. 아이디 중복 확인
        if (userRepository.existsByUsername(signUpRequest.username())==1) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_USERNAME);
        }

        // 2. 닉네임 중복 확인
        if (userRepository.existsByNickname(signUpRequest.nickname())==1) {
            throw new CustomException(ErrorCode.DUPLICATION_USER_NICKNAME);
        }

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpRequest.password());

        // 4. 엔티티 변환
        String imageUrl = "http://localhost:8080/images/default-profile.png";
        User user = User.builder()
                .username(signUpRequest.username())
                .password(encodedPassword)
                .name(signUpRequest.name())
                .nickname(signUpRequest.nickname())
                .email(signUpRequest.email())
                .phoneNumber(signUpRequest.phoneNumber())
                .role("USER")              // 디폴트 역할
                .profileImagePath(imageUrl) // 디폴트 이미지
                .passwordUpdatedAt(LocalDate.now()) // 디폴트 패스워드 업데이트 시간
                .build();

        // 5. 저장
        userRepository.insertUser(user);
    }
    @Override
    public boolean existsByUsername(String username) { return userRepository.existsByUsername(username) == 1; }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname)==1;
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
        userRepository.updatePassword(username, encodedNewPassword);
    }


    @Override
    public void updateMyInfo(String username, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findByUsername(username);
        if (user == null) {throw new CustomException(ErrorCode.USER_NOT_FOUND);}

        user.setNickname(userUpdateRequest.nickname());
        user.setEmail(userUpdateRequest.email());
        user.setPhoneNumber(userUpdateRequest.phoneNumber());

        // 프로필 이미지가 null이거나 isEmpty면 기본 이미지로 설정
        String userProfileImagePath = null;
        try {
            userProfileImagePath = userUpdateRequest.profileImagePath() == null || userUpdateRequest.profileImagePath().isEmpty() ?
                   DEFAULT_PROFILE_IMAGE_URL: uploadImageToS3(userUpdateRequest.profileImagePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setProfileImagePath(userProfileImagePath);
        
        userRepository.updateUser(user);
    }

    @Override
    public void deleteMyAccount(Long userId) {
        userRepository.deleteUser(userId);
    }

}
