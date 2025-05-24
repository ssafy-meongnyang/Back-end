package com.ssafy.meongnyang.api.user.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.user.dto.request.PasswordRequest;
import com.ssafy.meongnyang.api.user.dto.request.SignUpRequest;
import com.ssafy.meongnyang.api.user.dto.request.UserUpdateRequest;
import com.ssafy.meongnyang.api.user.dto.response.UserResponse;
import com.ssafy.meongnyang.api.user.service.UserService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/exists/username")
    public ApiResponseDto<Boolean> checkUsername(@RequestParam String username) {
        boolean isAvailable = !userService.existsByUsername(username);
        return ApiResponseDto.success(SuccessCode.CHECK_USERNAME_SUCCESS, isAvailable);
    }

    // 닉네임 중복 확인 API
    @GetMapping("/exists/nickname")
    public ApiResponseDto<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isAvailable = !userService.existsByNickname(nickname);
        return ApiResponseDto.success(SuccessCode.CHECK_NICKNAME_SUCCESS, isAvailable);
    }

    // 내 정보 보기 API
    @GetMapping("/me")
    public ApiResponseDto<UserResponse> getMyInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        String username = customUserDetails.getUsername();
        UserResponse userResponse= userService.getMyInfo(username);
        return ApiResponseDto.success(SuccessCode.USER_GET_INFO_SUCCESS, userResponse);
    }

    // 비밀번호 변경하기 API
    @PutMapping("/password")
    public ApiResponseDto<?> changePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody @Valid PasswordRequest passwordRequest){
        String username = customUserDetails.getUsername();
        userService.changePassword(username, passwordRequest);
        return ApiResponseDto.success(SuccessCode.USER_UPDATE_PASSWORD_SUCCESS);
    }

    // 내정보 수정하기 API
    @PatchMapping("/me")
    public ApiResponseDto<?> updateMyInfo(
            @RequestBody @Valid UserUpdateRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ){
        String username = customUserDetails.getUsername();
        UserResponse response = userService.updateMyInfo(username,request);
        return ApiResponseDto.success(SuccessCode.USER_UPDATE_MY_INFO_SUCCESS,response);
    }

    // 회원 탈퇴 API
    @DeleteMapping("/me")
    public ApiResponseDto<?> deleteMyAccount( @AuthenticationPrincipal CustomUserDetails customUserDetails){
        String username = customUserDetails.getUsername();
        userService.deleteMyAccount(username);
        return ApiResponseDto.success(SuccessCode.USER_DELETE_SUCCESS);
    }
}
