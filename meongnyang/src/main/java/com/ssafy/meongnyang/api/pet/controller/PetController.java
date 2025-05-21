package com.ssafy.meongnyang.api.pet.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.dto.response.PetListResponse;
import com.ssafy.meongnyang.api.pet.service.PetService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pet")
public class PetController {

    private final PetService petService;

    // 멍냥이 등록
    @PostMapping("")
    public ApiResponseDto<PetRequest> registerPet(@RequestBody @Valid PetRequest petRequest,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails){

        Long userId = userDetails.getUser().getId();
        petService.registerPet(userId,petRequest);
        return ApiResponseDto.success(SuccessCode.PET_REGISTER_SUCCESS,petRequest);
    }

    // 멍냥이 목록 조회
    @GetMapping("/list")
    public ApiResponseDto<?> getMyPets(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        List<PetListResponse> pets = petService.getPetListByUserId(userId);
        return ApiResponseDto.success(SuccessCode.PET_LIST_GET_SUCCESS, pets);
    }

    // 멍냥이 상세 조회
    @GetMapping("/{petId}")
    public ApiResponseDto<?> getPetDetail(@PathVariable Long petId){
        return ApiResponseDto.success(SuccessCode.PET_DETAIL_GET_SUCCESS, petService.getPetDetail(petId));
    }
}
