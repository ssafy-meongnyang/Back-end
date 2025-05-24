package com.ssafy.meongnyang.api.pet.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PetListResponse(
        Long id,
        String name,
        String breed,
        LocalDate birthDate,
        Integer weight,
        String profileImageUrl,
        boolean isRepresentative,
        List<String> healthConcerns
) {
}
