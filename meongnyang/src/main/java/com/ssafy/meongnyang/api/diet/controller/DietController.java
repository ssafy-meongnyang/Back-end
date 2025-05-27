package com.ssafy.meongnyang.api.diet.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.diet.domain.Diet;
import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.dto.request.DietUpdateRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietListResponse;
import com.ssafy.meongnyang.api.diet.dto.response.DietResponse;
import com.ssafy.meongnyang.api.diet.service.DietService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diet")
public class DietController {
    private final DietService dietService;

    // 식단 등록 하기
    @PostMapping("")
    public ApiResponseDto<?> createDiet(@AuthenticationPrincipal CustomUserDetails userDetails, @ModelAttribute DietRequest dietRequest){
        Long userId = userDetails.getUser().getId();
        dietService.createDiet(userId,dietRequest);
        return ApiResponseDto.success(SuccessCode.DIET_CREATE_SUCCESS);
    }

    // 식단 전체 조회하기
    @GetMapping("/list")
    public ApiResponseDto<?> getDietList(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        List<DietResponse> dietList = dietService.getDietList(userId);
        return ApiResponseDto.success(SuccessCode.DIET_LIST_GET_SUCCESS,dietList);
    }

    // 식단 상세 조회하기
    @GetMapping("/{dietId}")
    public ApiResponseDto<?> getDietDetail(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long dietId){
        Long userId = userDetails.getUser().getId();
        DietResponse dietResponse = dietService.getDietDetail(userId, dietId);
        return ApiResponseDto.success(SuccessCode.DIET_GET_DETAIL_SUCCESS,dietResponse);
    }

    // 식단 수정하기
    @PostMapping("/{dietId}")
    public ApiResponseDto<?> updateDiet(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long dietId, @ModelAttribute DietUpdateRequest dietUpdateRequest){
        Long userId = userDetails.getUser().getId();
        dietService.updateDiet(userId,dietId, dietUpdateRequest);
        return ApiResponseDto.success(SuccessCode.DIET_UPDATE_SUCCESS);
    }

    // 식단 삭제하기
    @DeleteMapping("/{dietId}")
    public ApiResponseDto<?> deleteDiet(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long dietId){
        Long userId = userDetails.getUser().getId();
        dietService.deleteDiet(userId,dietId);
        return ApiResponseDto.success(SuccessCode.DIET_DELETE_SUCCESS);
    }
}
