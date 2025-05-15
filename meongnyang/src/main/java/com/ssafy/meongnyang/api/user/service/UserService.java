package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void register(SignUpRequest userdto); //회원가입
}
