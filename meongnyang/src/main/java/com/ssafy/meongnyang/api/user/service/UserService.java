package com.ssafy.meongnyang.api.user.service;

import com.ssafy.meongnyang.api.user.dto.SignUpRequest;

public interface UserService {
    void register(SignUpRequest userdto);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
