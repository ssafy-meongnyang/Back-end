package com.ssafy.meongnyang.api.gpt.controller;

import com.ssafy.meongnyang.api.gpt.GptService;
import com.ssafy.meongnyang.api.gpt.dto.GptRequest;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;

    @PostMapping("/ai")
    public ApiResponseDto chatGPT(@RequestBody GptRequest gptRequest) {
        return ApiResponseDto.success(SuccessCode.AI_SUCCESS, gptService.chat(gptRequest.prompt()));
    }
}
