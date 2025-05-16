package com.ssafy.meongnyang.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    REGISTER_SUCCESS(201, HttpStatus.CREATED, "회원가입이 완료되었습니다."),
    CHECK_USERNAME_SUCCESS(200, HttpStatus.OK, "아이디 중복 확인 성공"),
    CHECK_NICKNAME_SUCCESS(200, HttpStatus.OK, "닉네임 중복 확인 성공"),

    //200 OK
    LOGIN_SUCCESS(200, HttpStatus.OK, "로그인 성공");


    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
