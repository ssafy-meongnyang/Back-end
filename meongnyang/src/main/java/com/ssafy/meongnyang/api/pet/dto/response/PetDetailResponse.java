package com.ssafy.meongnyang.api.pet.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PetDetailResponse(
        Long id,
        String name,
        String breed,
        LocalDate birthDate,
        String gender,
        Integer weight,
        String shape,
        Boolean allergic,
        String profileImagePath,
        boolean representative,
        List<String> allergens,
        List<String> healthConcerns
) {
}
