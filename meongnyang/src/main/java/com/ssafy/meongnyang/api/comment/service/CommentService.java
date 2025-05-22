package com.ssafy.meongnyang.api.comment.service;

import com.ssafy.meongnyang.api.comment.domain.Comment;
import com.ssafy.meongnyang.api.comment.dto.request.CommentCreateRequest;

public interface CommentService {
    void createComment(CommentCreateRequest request, Long boardId, Long userId);
}
