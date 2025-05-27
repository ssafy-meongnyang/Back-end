package com.ssafy.meongnyang.api.auth.controller;

import com.ssafy.meongnyang.api.auth.dto.request.LoginRequest;
import com.ssafy.meongnyang.api.auth.dto.response.LoginResponse;
import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.global.jwt.JwtTokenProvider;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ApiResponseDto<LoginResponse>  login(@RequestBody LoginRequest request) {
        // 1. 사용자 아이디와 비밀번호로 인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( //인증을 위한 스프링 시큐리티 표준 토큰
                        request.getUsername(), request.getPassword()
                )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // 2. 인증에 성공하면 JWT 토큰 생성
        // Role은 ROLE_USER 형식이므로 "ROLE_" 접두사 제거 후 전달
        String token = jwtTokenProvider.createToken(
                authentication.getName(),           // username
                authentication.getAuthorities().iterator().next().getAuthority().replace("ROLE_", ""), // role
                userDetails.getUserId(),
                userDetails.getUser().getNickname()
        );

        // 3. 응답 객체 생성 (accessToken 담은 DTO)
        LoginResponse loginResponse = new LoginResponse(token);
        // 커스텀 ResponseDto 형태로 응답
        return ApiResponseDto.success(SuccessCode.LOGIN_SUCCESS, loginResponse);
    }

    // 클라이언트에서 JWT 삭제하는 방식
    @PostMapping("/logout")
    public ApiResponseDto<?> logout(HttpServletRequest request) {
        // 실제로는 클라이언트가 Authorization 헤더에서 토큰을 제거해야 함
        return ApiResponseDto.success(SuccessCode.LOGOUT_SUCCESS);
    }


}
