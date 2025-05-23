package com.ssafy.meongnyang.api.diet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diet {
    private Long dietId;
    private Long userId; // 유저 식별자 (외래키 역할)

    private LocalDate date;

    private String breakfastImgPath; // 이미지 파일 경로 (ex: "/images/breakfast123.jpg")
    private String breakfastDes;

    private String lunchImgPath;
    private String lunchDes;

    private String dinnerImgPath;
    private String dinnerDes;

    private String snack;
    private String memo;
    private String exercise;
}
