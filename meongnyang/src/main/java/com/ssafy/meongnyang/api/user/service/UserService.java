package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.dto.request.PasswordRequest;
import com.ssafy.meongnyang.api.user.dto.request.SignUpRequest;
import com.ssafy.meongnyang.api.user.dto.request.UserUpdateRequest;
import com.ssafy.meongnyang.api.user.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(SignUpRequest signUpRequest); //회원가입
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    UserResponse getMyInfo(String username);
    void changePassword(String username, PasswordRequest passwordRequest);
    void updateMyInfo(String username, UserUpdateRequest userUpdateRequest);
    void deleteMyAccount(Long userId);
}
