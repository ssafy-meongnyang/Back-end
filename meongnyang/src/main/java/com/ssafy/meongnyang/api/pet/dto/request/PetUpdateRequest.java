package com.ssafy.meongnyang.api.pet.dto.request;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record PetUpdateRequest(
        String name,
        String breed,
        LocalDate birthDate,
        String gender,
        Integer weight,
        String shape,
        Boolean allergic,
        MultipartFile profileImagePath,
        Boolean representative,
        List<String> allergens,
        List<String> healthConcerns
) {
}
