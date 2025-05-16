package com.ssafy.meongnyang.api.user.repository;

import com.ssafy.meongnyang.api.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    // 회원 저장
    void insertUser(User user);

    // username(아이디)로 사용자 조회 (로그인용)
    User findByUsername(String username);

    // username(아이디) 중복 확인
    boolean existsByUsername(String username);

    // nickname 중복 확인
    boolean existsByNickname(String nickname);
}
