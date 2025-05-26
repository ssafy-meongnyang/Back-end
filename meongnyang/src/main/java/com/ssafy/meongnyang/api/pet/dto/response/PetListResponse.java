package com.ssafy.meongnyang.api.pet.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record PetListResponse(
        Long id,
        String name,
        String breed,
        String gender,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthDate, // 나이 계산용
        Integer weight,
        String profileImagePath,
        Boolean representative,
        List<String> healthConcerns
) {
}
