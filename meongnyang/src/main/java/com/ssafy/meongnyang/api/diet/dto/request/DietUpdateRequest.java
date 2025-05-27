package com.ssafy.meongnyang.api.diet.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietUpdateRequest {
    
    // 이미지 삭제를 감지하기 위한 플래그 추가
    Boolean breakfastImgDelete;
    Boolean lunchImgDelete;
    Boolean dinnerImgDelete;

    Long dietId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    LocalDate date;
    @NotNull
    String title;
    @Nullable
    MultipartFile breakfastImg;
    @Nullable
    String breakfastDes;
    @Nullable
    MultipartFile lunchImg;
    @Nullable
    String lunchDes;
    @Nullable
    MultipartFile dinnerImg;
    @Nullable
    String dinnerDes;
    @Nullable
    String snack;
    @Nullable
    String memo;
    @Nullable
    String exercise;
}
