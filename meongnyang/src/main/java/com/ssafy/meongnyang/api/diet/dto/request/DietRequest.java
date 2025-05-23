package com.ssafy.meongnyang.api.diet.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietRequest {
    LocalDate date;
    String title;
    MultipartFile breakfastImg;
    String breakfastDes;
    MultipartFile lunchImg;
    String lunchDes;
    MultipartFile dinnerImg;
    String dinnerDes;
    String snack;
    String memo;
    String exercise;
}
