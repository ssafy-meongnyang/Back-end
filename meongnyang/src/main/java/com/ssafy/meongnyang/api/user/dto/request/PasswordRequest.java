package com.ssafy.meongnyang.api.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PasswordRequest(


        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String currentPassword,
        @NotBlank(message = "새로운 비밀번호 입력은 필수입니다.")
//        시연을 위해 주석처리        
//        @Pattern(
//                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,20}$",
//                message = "비밀번호는 영문자, 숫자, 특수문자 포함 최소 8~20자여야 합니다."
//        )
        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String newPassword,
        @NotBlank(message = "비밀번호 확인은 필수입니다.")
        String confirmPassword
) {
}
