package com.ssafy.meongnyang.api.comment.controller;

import com.ssafy.meongnyang.api.comment.dto.request.CommentCreateRequest;
import com.ssafy.meongnyang.api.comment.dto.request.CommentUpdateRequest;
import com.ssafy.meongnyang.api.comment.service.CommentServiceImpl;
import com.ssafy.meongnyang.global.common.annotation.UserId;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    @PostMapping("/board/{boardId}/comment")
    public ApiResponseDto createComment(@RequestBody CommentCreateRequest commentCreateRequest,
                                        @PathVariable Long boardId,
                                        @UserId Long userId) {
        commentServiceImpl.createComment(commentCreateRequest, boardId, userId);
        return ApiResponseDto.success(SuccessCode.COMMENT_CREATE_SUCCESS);
    }

    @GetMapping("/board/{boardId}/comment")
    public ApiResponseDto getComment(@PathVariable Long boardId) {
        return ApiResponseDto.success(SuccessCode.COMMENT_LIST_GET_SUCCESS, commentServiceImpl.getComment(boardId));
    }

    @PatchMapping("/comment/{commentId}")
    public ApiResponseDto updateComment(@RequestBody CommentUpdateRequest commentUpdateRequest,
                                        @PathVariable Long commentId,
                                        @UserId Long userId) {
        commentServiceImpl.updateComment(commentUpdateRequest, commentId, userId);
        return ApiResponseDto.success(SuccessCode.COMMENT_UPDATE_SUCCESS);
    }

    @DeleteMapping("/comment/{commentId}")
    public ApiResponseDto deleteComment(@PathVariable Long commentId,
                                        @UserId Long userId) {
        commentServiceImpl.deleteComment(commentId, userId);
        return ApiResponseDto.success(SuccessCode.COMMENT_DELETE_SUCCESS);
    }
}
