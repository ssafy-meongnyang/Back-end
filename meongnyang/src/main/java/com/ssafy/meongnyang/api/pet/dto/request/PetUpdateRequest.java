package com.ssafy.meongnyang.api.pet.dto.request;

import java.time.LocalDate;
import java.util.List;

public record PetUpdateRequest(
        String name,
        String breed,
        LocalDate birthDate,
        String gender,
        Integer weight,
        String shape,
        Boolean isAllergic,
        String profileImageUrl,
        List<String> allergens,
        List<String> healthConcerns
) {
}
