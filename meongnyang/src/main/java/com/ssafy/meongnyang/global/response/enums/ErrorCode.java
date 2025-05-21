package com.ssafy.meongnyang.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //400 BAD REQUEST
    BAD_REQUEST(400,HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    MISSING_REQUIRED_HEADER(400, HttpStatus.BAD_REQUEST, "필수 헤더가 누락되었습니다."),
    MISSING_REQUIRED_PARAMETER(400, HttpStatus.BAD_REQUEST, "필수 파라미터가 누락되었습니다."),
    DUPLICATION_USER_USERNAME(400, HttpStatus.BAD_REQUEST, "중복된 아이디입니다."),
    DUPLICATION_USER_NICKNAME(400, HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
    EMAIL_VERIFICATION_FAILED(400, HttpStatus.BAD_REQUEST,"인증 코드가 일치하지 않거나 만료되었습니다."),
    PASSWORD_CONFIRM_NOT_MATCH(400,HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    INVALID_PASSWORD(400,HttpStatus.BAD_REQUEST, "현재 비밀번호가 정확하지 않습니다."),
    //404 NOT FOUND
    NOT_FOUND_END_POINT(404, HttpStatus.NOT_FOUND, "엔드포인트를 찾을 수 없습니다."),

    //405 METHOD NOT ALLOWED
    METHOD_NOT_ALLOWED(40500, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 메소드입니다."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
