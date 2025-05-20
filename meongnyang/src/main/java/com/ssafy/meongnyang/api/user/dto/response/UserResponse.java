package com.ssafy.meongnyang.api.user.dto.response;

public record UserResponse(
    String username,
    String nickname, // 닉네임 보여주기
    String email,
    String phonenumber
) {
}
