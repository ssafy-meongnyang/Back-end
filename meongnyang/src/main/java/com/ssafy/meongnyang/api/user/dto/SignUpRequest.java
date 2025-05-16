package com.ssafy.meongnyang.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
// jakarta랑 hibernate 의존성 확인하기
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String phonenumber;
    private String email;

}
