package com.ssafy.meongnyang.api.pet.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.service.PetService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet")
public class PetController {

    private final PetService petService;

    @PostMapping("")
    public ApiResponseDto<PetRequest> registerPet(@RequestBody @Valid PetRequest petRequest,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails){

        Long userId = userDetails.getUser().getId();
        petService.registerPet(userId,petRequest);
        return ApiResponseDto.success(SuccessCode.PET_REGISTER_SUCCESS,petRequest);
    }
}
