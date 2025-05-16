package com.ssafy.meongnyang.api.auth;

import com.ssafy.meongnyang.api.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Spring Security에서 인증을 위해 사용하는 사용자 정보 클래스
public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    // 로그인 사용자가 어떤 권한을 가지고 있는지 확인
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 스프링 시큐리티는 ROLE_이 붙은 문자열만 역할(Role)로 인식하기 때문에 진행함
        // 권한 정보 확인(ex. user의 role을 가져와서 대문자로 변환한 후 앞에 "ROLE_" 연결하기)
        return Collections.singleton(()->"ROLE_" + user.getRole().toUpperCase());
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 암호화된 비밀번호
    }

    @Override
    public String getUsername() {
        return user.getUsername(); // 로그인용 아이디
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠김 여부 (true는 사용가능
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
