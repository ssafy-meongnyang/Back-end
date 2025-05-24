package com.ssafy.meongnyang.api.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record SignUpRequest (

    @NotBlank(message = "이름은 필수입니다.")
    String name,

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    String username,

    //    시연을 위한 주석처리
//    @Pattern(
//            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
//            message = "비밀번호는 영문자, 숫자, 특수문자 포함 최소 8~20자여야 합니다."
//    )
    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password,

    @NotBlank(message = "비밀번호 확인은 필수입니다.")
    String confirmPassword,

    @NotBlank(message = "닉네임은 필수입니다.")
    String nickname,

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(
            regexp = "^(010)[0-9]{7,8}$",
            message = "유효한 전화번호 형식이어야 합니다. (예: 01012345678)"
    )
    String phoneNumber,

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    String email

){
}
