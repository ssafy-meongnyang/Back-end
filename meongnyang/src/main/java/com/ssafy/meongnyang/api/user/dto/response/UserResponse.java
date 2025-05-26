package com.ssafy.meongnyang.api.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record UserResponse(
    String username,
    String nickname, // 화면에는 닉네임 보여주기
    String email,
    String phoneNumber,
    String profileImagePath,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate passwordUpdatedAt
) {
}
