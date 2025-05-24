package com.ssafy.meongnyang.api.diet.dto.response;

import java.time.LocalDate;

public record DietResponse (
        Long dietId,
        Long userId,
        LocalDate date,
        String title,
        String breakfastImg,
        String breakfastDes,
        String lunchImg,
        String lunchDes,
        String dinnerImg,
        String dinnerDes,
        String snack,
        String memo,
        String exercise
){}
