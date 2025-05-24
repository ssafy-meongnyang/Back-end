package com.ssafy.meongnyang.api.pet.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetRequest{

        @NotBlank(message = "멍냥이 이름을 입력해주세요.")
        String name;

        @NotBlank(message = "멍종 냥종을 입력해주세요.")
        String breed;

        @Nullable
        LocalDate birthDate; // 생일 모를경우 null

        @NotBlank(message = "남아인지 여아인지 선택해주세요. 중성화한 경우도 동일하게 입력해요.")
        @Pattern(regexp="남아|여아",message = "둘 중 하나만 선택해주세요")
        String gender;

        @Nullable
        Integer weight; // 몸무게 모를경우 null

        @NotBlank(message = "체형을 입력해주세요.")
        @Pattern(regexp = "날씬|통통|뚱", message="셋 중 하나만 입력해주세요.")
        String shape;

        @Nullable
        List<String> healthConcerns;

        @NotNull(message = "알러지 여부를 입력해주세요.")
        Boolean allergic;

        @Nullable
        List<String> allergens;

        @Nullable
        MultipartFile profileImagePath;
}
