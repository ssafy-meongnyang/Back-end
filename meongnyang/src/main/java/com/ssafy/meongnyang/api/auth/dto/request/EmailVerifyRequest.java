package com.ssafy.meongnyang.api.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailVerifyRequest(
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 유효하지 않습니다.")
        String email,

        @NotBlank(message = "인증코드는 필수입니다.")
        String code
) {
}
