package com.ssafy.meongnyang.api.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserUpdateRequest (
    @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
    String nickname,

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotBlank(message = "휴대폰 번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^010\\d{8}$")
    String phonenumber,

    String profileImageUrl
){ }
