package com.ssafy.meongnyang.api.user.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
// 아이디는 db에서 auto increment 되는건지? 아니면 여기에서 늘려주는건지?
    private Long id;
    private String name; // 이름
    private String username; // 아이디
    private String nickname; // 닉네임
    private String phonenumber; // 휴대폰 번호
    private String password;
    private String email;
    private LocalDateTime createdAt;

}
