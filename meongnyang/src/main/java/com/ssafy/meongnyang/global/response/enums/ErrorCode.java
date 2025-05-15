package com.ssafy.meongnyang.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(400,HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(400, HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    MISSING_REQUIRED_PARAMETER(400, HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),

    NOT_FOUND_END_POINT(404, HttpStatus.NOT_FOUND, "엔드포인트를 찾을 수 없습니다."),

    METHOD_NOT_ALLOWED(40500, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드입니다."),
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
