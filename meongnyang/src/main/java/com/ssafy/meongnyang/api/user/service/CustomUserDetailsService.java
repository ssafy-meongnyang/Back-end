package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.auth.CustomUserDetails;
import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 사용자 조회 후 UserDetails로 변환하는 클래스
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다." + username);
        }
        return new CustomUserDetails(user);
    }
}
