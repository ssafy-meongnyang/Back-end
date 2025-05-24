package com.ssafy.meongnyang.api.user.repository;

import com.ssafy.meongnyang.api.user.domain.User;
import com.ssafy.meongnyang.api.user.dto.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    // 회원 가입
    void insertUser(User user); // 회원 저장
    int existsByUsername(String username); // username(아이디) 중복 확인
    int existsByNickname(String nickname); // nickname 중복 확인

    // 로그인
    User findByUsername(String username);

    // username(아이디)로 사용자 정보 조회
    UserResponse selectUserByUsername(String username);
    
    // password 변경
    int updatePassword(@Param("username") String username, @Param("password") String newPassword);
    
    // username으로 사용자 정보 수정
    int updateUser(User user);
    int deleteUser(String username);
}
