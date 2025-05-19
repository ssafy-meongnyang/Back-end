package com.ssafy.meongnyang.api.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignUpRequest {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @NotBlank(message = "아이디는 필수입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^(010)[0-9]{7,8}$", message = "유효한 전화번호 형식이어야 합니다. (예: 01012345678)")
    private String phonenumber;
    @NotBlank(message="이메일은 필수입니다.")
    @Email(message="이메일 형식이 유효하지 않습니다.")
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
