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
public class DietRequest {
    @NotNull
    LocalDate date;
    @NotNull
    String title;
    @Nullable
    MultipartFile breakfastImgPath;
    @Nullable
    String breakfastDes;
    @Nullable
    MultipartFile lunchImgPath;
    @Nullable
    String lunchDes;
    @Nullable
    MultipartFile dinnerImgPath;
    @Nullable
    String dinnerDes;
    @Nullable
    String snack;
    @Nullable
    String memo;
    @Nullable
    String exercise;
}
