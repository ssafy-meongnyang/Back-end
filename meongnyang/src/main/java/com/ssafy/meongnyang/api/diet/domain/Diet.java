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
    private Long userId;

    private LocalDate date;
    private String title;
    private String breakfastImgPath;
    private String breakfastDes;

    private String lunchImgPath;
    private String lunchDes;

    private String dinnerImgPath;
    private String dinnerDes;

    private String snack;
    private String memo;
    private String exercise;
}
