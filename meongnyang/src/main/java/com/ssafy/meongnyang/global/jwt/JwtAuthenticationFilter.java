package com.ssafy.meongnyang.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 요청 헤더에서 JWT 추출
        String token = jwtTokenProvider.resolveToken(request);

        // 2. 유효한 토큰인지 확인
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 토큰에서 사용자 정보 추출 (인증 객체 생성)
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            // 4. SecurityContext에 인증 정보 저장 → 이후 인증된 사용자로 인식됨
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 5. 다음 필터로 전달
        filterChain.doFilter(request, response);
    }
}
