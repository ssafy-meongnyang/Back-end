package com.ssafy.meongnyang.api.pet.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.dto.request.PetUpdateRequest;
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

    // 멍냥이 등록하기
    @PostMapping("")
    public ApiResponseDto<PetRequest> registerPet(@ModelAttribute PetRequest petRequest,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails){

        Long userId = userDetails.getUser().getId();
        petService.registerPet(userId,petRequest);
        return ApiResponseDto.success(SuccessCode.PET_REGISTER_SUCCESS);
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

    // 멍냥이 정보 수정
    @PatchMapping("/{petId}")
    public ApiResponseDto<?> updatePetInfo(
            @PathVariable Long petId,
            @RequestBody PetUpdateRequest petUpdateRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        Long userId = userDetails.getUser().getId();
        petService.updatePetInfo(userId, petId ,petUpdateRequest);
        return ApiResponseDto.success(SuccessCode.PET_UPDATE_SUCCESS);
    }

    // 멍냥이 데이터 삭제
    @DeleteMapping("/{petId}")
    public ApiResponseDto<?> deletePetdata(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long petId
            ){
        Long userId = userDetails.getUser().getId();
        petService.deletePetdata(userId, petId);
        return ApiResponseDto.success(SuccessCode.PET_DELETE_SUCCESS);
    }
}
