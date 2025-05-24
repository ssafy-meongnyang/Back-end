package com.ssafy.meongnyang.api.diet.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietUpdateRequest {
    Long dietId;
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
