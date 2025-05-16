package com.ssafy.meongnyang.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //200 OK
    LOGIN_SUCCESS(200, HttpStatus.OK, "로그인 성공"),

    //201 Created
    BOARD_CREATE_SUCCESS(201, HttpStatus.CREATED, "게시물 생성 성공")
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
