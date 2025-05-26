package com.ssafy.meongnyang.api.comment.service;

import com.ssafy.meongnyang.api.comment.domain.Comment;
import com.ssafy.meongnyang.api.comment.dto.request.CommentCreateRequest;
import com.ssafy.meongnyang.api.comment.dto.request.CommentUpdateRequest;
import com.ssafy.meongnyang.api.comment.dto.response.CommentGetResponse;
import java.util.List;

public interface CommentService {
    void createComment(CommentCreateRequest request, Long boardId, Long userId);
    List<CommentGetResponse> getComment(Long boardId);
    void updateComment(CommentUpdateRequest request, Long commentId, Long userId);
    Comment getCommentById(Long commentId);
    void deleteComment(Long commentId, Long userId);
}
