package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(SignUpRequest userdto); //회원가입
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
