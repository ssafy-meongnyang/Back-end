package com.ssafy.meongnyang.api.board.controller;

import com.ssafy.meongnyang.api.board.dto.request.BoardCreateRequest;
import com.ssafy.meongnyang.api.board.dto.request.BoardUpdateRequest;
import com.ssafy.meongnyang.api.board.dto.response.BoardGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.BoardListGetResponse;
import com.ssafy.meongnyang.api.board.dto.response.PageResponse;
import com.ssafy.meongnyang.api.board.service.BoardServiceImpl;
import com.ssafy.meongnyang.global.common.annotation.UserId;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

    private final BoardServiceImpl boardServiceImpl;

    //게시물 등록
    @PostMapping("/board")
    public ApiResponseDto createBoard(@ModelAttribute BoardCreateRequest boardCreateRequest, @UserId Long userId) {
        boardServiceImpl.createBoard(boardCreateRequest, userId);
        return ApiResponseDto.success(SuccessCode.BOARD_CREATE_SUCCESS);
    }

    //게시물 전체 리스트 조회
    @GetMapping("/board/list")
    public ApiResponseDto getBoardList(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam String category) {
        PageResponse<BoardListGetResponse> boardList = boardServiceImpl.getBoardList(page, size, category);
        return ApiResponseDto.success(SuccessCode.BOARD_LIST_GET_SUCCESS, boardList);
    }

    //게시물 상세 조회
    @GetMapping("/board/{boardId}")
    public ApiResponseDto<BoardGetResponse> getBoard(@PathVariable Long boardId) {
        return ApiResponseDto.success(SuccessCode.BOARD_GET_SUCCESS, boardServiceImpl.getBoardById(boardId));
    }

    //게시물 수정
    @PatchMapping("/board/{boardId}")
    public ApiResponseDto updateBoard(@ModelAttribute BoardUpdateRequest boardUpdateRequest, @PathVariable Long boardId){
        boardServiceImpl.updateBoard(boardUpdateRequest, boardId);
        return ApiResponseDto.success(SuccessCode.BOARD_UPDATE_SUCCESS);
    }

    //게시물 삭제
    @DeleteMapping("/board/{boardId}")
    public ApiResponseDto deleteBoard(@PathVariable Long boardId) {
        boardServiceImpl.deleteBoard(boardId);
        return ApiResponseDto.success(SuccessCode.BOARD_DELETE_SUCCESS);
    }

    @GetMapping("/board/search")
    public ApiResponseDto searchBoard(@RequestParam (required = false) String writer,
                                      @RequestParam (required = false) String title,
                                      @RequestParam (required = false) String content){
        return ApiResponseDto.success(SuccessCode.BOARD_SEARCH_SUCCESS, boardServiceImpl.searchBoard(writer, title, content));
    }
}
