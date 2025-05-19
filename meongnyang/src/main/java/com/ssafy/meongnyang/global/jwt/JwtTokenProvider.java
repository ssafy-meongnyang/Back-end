package com.ssafy.meongnyang.global.jwt;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.token-validity}")
    private long tokenValidityInMilliseconds;
    private Key key;

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes); // key 세팅
    }


    // 토큰 생성 메서드
    public String createToken(String username, String role) {
        // JWT Payload에 담을 클레임 설정 (username은 subject로, role은 추가 속성으로)
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role); // 사용자 권한 정보 추가

        Date now = new Date(); // 현재 시간
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds); // 만료 시간

        // 토큰 생성 및 서명
        return Jwts.builder()
                .setClaims(claims)              // 사용자 정보 클레임
                .setIssuedAt(now)               // 발급 시간
                .setExpiration(validity)        // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명 알고리즘 및 키
                .compact();                     // 최종 문자열 반환
    }

    // 토큰으로부터 인증 객체(Authentication) 추출
    public Authentication getAuthentication(String token) {
        String username = getUsername(token); // 토큰에서 username 추출
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 username(subject)을 추출
    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true; // 파싱 성공 → 유효한 토큰
        } catch (JwtException | IllegalArgumentException e) {
            return false; // 서명 불일치, 만료 등 → 유효하지 않은 토큰
        }
    }

    // HTTP 요청 헤더에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        // "Bearer eyJ..." 형태에서 토큰만 추출
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7); // "Bearer " 제거 후 순수 토큰 반환
        }
        return null;
    }
}
