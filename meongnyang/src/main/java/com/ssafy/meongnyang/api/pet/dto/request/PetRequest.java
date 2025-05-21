package com.ssafy.meongnyang.api.pet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public record PetRequest(
        @NotBlank(message = "멍냥이 이름을 입력해주세요.")
        String name,
        @NotBlank(message = "멍냥이 종류를 입력해주세요.")
        String breed,
        @NotNull(message = "생일을 입력해주세요.")
        LocalDate birthDate,
        @Pattern(regexp="남아|여아",message = "둘 중 하나만 선택해주세요")
        String gender,
        @NotNull(message = "몸무게를 입력해주세요.")
        int weight,
        @Pattern(regexp = "날씬|통통|뚱", message="셋 중 하나만 입력해주세요.")
        String shape,

        List<String> healthConcerns,
        Boolean isAllergic,
        List<String> allergens,
        String profileImageUrl
) {
}
