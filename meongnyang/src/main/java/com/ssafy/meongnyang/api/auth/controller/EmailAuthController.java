package com.ssafy.meongnyang.api.auth.controller;

import com.ssafy.meongnyang.api.auth.dto.request.EmailRequest;
import com.ssafy.meongnyang.api.auth.dto.request.EmailVerifyRequest;
import com.ssafy.meongnyang.api.auth.service.EmailService;
import com.ssafy.meongnyang.api.auth.service.EmailVerificationStore;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/email")
@RequiredArgsConstructor
public class EmailAuthController {
    private final EmailService emailService;
    private final EmailVerificationStore verificationStore;

    // 인증 코드 전송
    @PostMapping("/send")
    public ApiResponseDto<?> sendVerificationEmail(@RequestBody EmailRequest request){
        emailService.sendVerificationCode(request.email());
        return ApiResponseDto.success(SuccessCode.EMAIL_SENT);
    }

    // 인증 코드 확인
    @PostMapping("/verify")
    public ApiResponseDto<?> verifyEmailCode(@RequestBody EmailVerifyRequest request){
        boolean verified = verificationStore.verify(request.email(), request.code());
        if(!verified){
            throw new CustomException(ErrorCode.EMAIL_VERIFICATION_FAILED);
        }
        return ApiResponseDto.success(SuccessCode.EMAIL_VERIFIED);
    }

}
