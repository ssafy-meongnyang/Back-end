package com.ssafy.meongnyang.api.comment.controller;

import com.ssafy.meongnyang.api.comment.dto.request.CommentCreateRequest;
import com.ssafy.meongnyang.api.comment.service.CommentServiceImpl;
import com.ssafy.meongnyang.global.common.annotation.UserId;
import com.ssafy.meongnyang.global.response.ApiResponseDto;
import com.ssafy.meongnyang.global.response.enums.SuccessCode;
import lombok.RequiredArgsConstructor;
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
}
