package com.ssafy.meongnyang.api.pet.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PetListResponse(
        Long id,
        String name,
        String breed,
        String gender,
        LocalDate birthDate, // 나이 계산용
        Integer weight,
        String profileImagePath,
        Boolean representative,
        List<String> healthConcerns
) {
}
