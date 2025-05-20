package com.ssafy.meongnyang.api.user.controller;

import com.ssafy.meongnyang.api.user.dto.SignUpRequest;
import com.ssafy.meongnyang.api.user.service.UserService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입 API
    @PostMapping("/register")
    public ApiResponseDto<?> register(@RequestBody @Valid SignUpRequest signUpRequest) {
        userService.register(signUpRequest);
        return ApiResponseDto.success(SuccessCode.REGISTER_SUCCESS);
    }

    // 아이디 중복 확인 API
    @GetMapping("/check-username")
    public ApiResponseDto<Boolean> checkUsername(@RequestParam String username) {
        boolean isAvailable = !userService.existsByUsername(username);
        return ApiResponseDto.success(SuccessCode.CHECK_USERNAME_SUCCESS, isAvailable);
    }

    // 닉네임 중복 확인 API
    @GetMapping("/check-nickname")
    public ApiResponseDto<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = !userService.existsByNickname(nickname);
        return ApiResponseDto.success(SuccessCode.CHECK_NICKNAME_SUCCESS, isAvailable);
    }
}
