package com.ssafy.meongnyang.api.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {
// jakarta랑 hibernate 의존성 확인하기
    private String name;
    private String username;
    private String password;
    private String nickname;
    private String phonenumber;
    private String email;

    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
