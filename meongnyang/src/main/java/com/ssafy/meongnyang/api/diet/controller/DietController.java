package com.ssafy.meongnyang.api.diet.controller;

import com.ssafy.meongnyang.api.auth.security.CustomUserDetails;
import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.service.DietService;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diet")
public class DietController {
    private final DietService dietService;

    @PostMapping("")
    public ApiResponseDto<?> createDiet(@AuthenticationPrincipal CustomUserDetails userDetails, @ModelAttribute DietRequest dietRequest){
        Long userId = userDetails.getUser().getId();
        dietService.createDiet(userId,dietRequest);
        return ApiResponseDto.success(SuccessCode.DIET_CREATE_SUCCESS);
    }

}
