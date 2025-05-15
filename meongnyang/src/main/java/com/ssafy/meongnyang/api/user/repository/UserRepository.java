package com.ssafy.meongnyang.api.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.meongnyang.api.user.domain.User;

@Mapper
public interface UserRepository {
    // 회원가입
    void insertUser(User user);
    // 내 정보 보기
    // 내 정보 수정
    // 비밀 번호 변경
    // 아이디 중복 확인
    boolean existsById(String username);
    // 닉네임 중복 확인
    boolean existsByNickName(String nickName);

}
