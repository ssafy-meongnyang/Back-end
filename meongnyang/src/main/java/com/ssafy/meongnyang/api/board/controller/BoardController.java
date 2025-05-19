package com.ssafy.meongnyang.api.board.controller;

import com.ssafy.meongnyang.api.board.dto.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.service.BoardServiceImpl;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    @PostMapping("/board")
    public ApiResponseDto createBoard(@ModelAttribute BoardCreateRequest boardCreateRequest) {
        boardServiceImpl.createBoard(boardCreateRequest);
        return ApiResponseDto.success(SuccessCode.BOARD_CREATE_SUCCESS);
    }

    @GetMapping("/board/list")
    public ApiResponseDto getBoardList() {
        return ApiResponseDto.success(SuccessCode.BOARD_LIST_GET_SUCCESS, boardServiceImpl.getBoardList());
    }
}
