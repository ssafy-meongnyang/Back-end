package com.ssafy.meongnyang.api.user.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class User{
    private Long id;
    private String name; // 이름
    private String nickname; // 닉네임
    private String username; // 아이디
    private String password; // 비밀번호
    private String phonenumber; // 휴대폰 번호
    private String email; // 이메일
    private String role; // 역할
    private String profileImageUrl; // 프로필 이미지 
    private LocalDate passwordUpdatedAt; // 비밀번호 업데이트 시간



}
