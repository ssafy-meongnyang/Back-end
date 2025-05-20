package com.ssafy.meongnyang.api.user.domain;

import com.ssafy.meongnyang.global.common.BaseTimeEntity;
import java.time.LocalDateTime;

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
    private String username; // 아이디
    private String nickname; // 닉네임
    private String phonenumber; // 휴대폰 번호
    private String password;
    private String email;
    private String profileImageUrl;
    private String role;
}
