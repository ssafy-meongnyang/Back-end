package com.ssafy.meongnyang.global.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    //200 OK
    EMAIL_SENT(200,HttpStatus.OK,"인증 코드가 전송되었습니다."),
    EMAIL_VERIFIED(200,HttpStatus.OK,"이메일 인증이 완료되었습니다."),
    LOGIN_SUCCESS(200, HttpStatus.OK, "로그인 성공"),
    CHECK_USERNAME_SUCCESS(200, HttpStatus.OK, "아이디 중복 확인 성공"),
    CHECK_NICKNAME_SUCCESS(200, HttpStatus.OK, "닉네임 중복 확인 성공"),
    BOARD_LIST_GET_SUCCESS(200, HttpStatus.OK, "게시물 전체 리스트 조회 성공"),
    LOGOUT_SUCCESS(200,HttpStatus.OK , "로그아웃이 완료되었습니다."),
    BOARD_GET_SUCCESS(200, HttpStatus.OK, "게시물 상세 조회 성공"),
    BOARD_UPDATE_SUCCESS(200, HttpStatus.OK, "게시물 수정 성공"),
    BOARD_DELETE_SUCCESS(200, HttpStatus.OK, "게시물 삭제 성공"),
    BOARD_SEARCH_SUCCESS(200, HttpStatus.OK, "게시물 검색 성공"),
    USER_GET_INFO_SUCCESS(200, HttpStatus.OK, "마이페이지 내 정보 조회 성공"),
    USER_UPDATE_PASSWORD_SUCCESS(200, HttpStatus.OK, "비밀번호가 성공적으로 변경되었습니다."),
    USER_UPDATE_MY_INFO_SUCCESS(200,HttpStatus.OK,"마이페이지 내 정보 수정 성공"),
    USER_DELETE_SUCCESS(200,HttpStatus.OK,"회원 탈퇴 성공"),
    PET_LIST_GET_SUCCESS(200,HttpStatus.OK,"멍냥이 목록 조회 성공"),
    PET_DETAIL_GET_SUCCESS(200,HttpStatus.OK,"멍냥이 상세조회 성공"),
    PET_UPDATE_SUCCESS(200,HttpStatus.OK,"멍냥이 정보 수정 성공"),
    PET_DELETE_SUCCESS(200,HttpStatus.OK,"멍냥 데이터 삭제 성공"),
    COMMENT_LIST_GET_SUCCESS(200, HttpStatus.OK, "댓글 전체 조회 성공"),
    COMMENT_UPDATE_SUCCESS(200, HttpStatus.OK, "댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS(200, HttpStatus.OK, "댓글 삭제 성공"),
    DIET_LIST_GET_SUCCESS(200,HttpStatus.OK,"식단 전체 조회 성공"),
    DIET_GET_DETAIL_SUCCESS(200,HttpStatus.OK,"식단 상세 조회 성공"),
    DIET_UPDATE_SUCCESS(200,HttpStatus.OK,"식단 수정 성공"),
    DIET_DELETE_SUCCESS(200,HttpStatus.OK,"식단 삭제 성공"),
    AI_SUCCESS(200, HttpStatus.OK, "AI 응답 성공"),

    //201 CREATED
    BOARD_CREATE_SUCCESS(201, HttpStatus.CREATED, "게시물 생성 성공"),
    REGISTER_SUCCESS(201, HttpStatus.CREATED, "회원가입이 완료되었습니다."),
    PET_REGISTER_SUCCESS(201, HttpStatus.CREATED,"멍냥이 등록이 완료되었습니다."),
    COMMENT_CREATE_SUCCESS(201, HttpStatus.CREATED, "댓글 등록 성공"),
    DIET_CREATE_SUCCESS(201, HttpStatus.CREATED,"식단 등록 성공")
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
