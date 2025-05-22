package com.ssafy.meongnyang.api.comment.service;

import com.ssafy.meongnyang.api.comment.domain.Comment;
import com.ssafy.meongnyang.api.comment.dto.request.CommentCreateRequest;
import com.ssafy.meongnyang.api.comment.dto.request.CommentUpdateRequest;
import com.ssafy.meongnyang.api.comment.dto.response.CommentGetResponse;
import com.ssafy.meongnyang.api.comment.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public void createComment(CommentCreateRequest commentCreateRequest, Long boardId, Long userId) {
        Comment comment = Comment.builder()
                .boardId(boardId)
                .userId(userId)
                .content(commentCreateRequest.content())
                .build();
        commentRepository.createComment(comment);
    }

    public List<CommentGetResponse> getComment(Long boardId) {
        return commentRepository.getComment(boardId);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.getCommentById(commentId);
    }

    public void updateComment(CommentUpdateRequest commentUpdateRequest, Long commentId) {
        Comment comment = commentRepository.getCommentById(commentId);
        comment.setContent(commentUpdateRequest.content());
        commentRepository.updateComment(comment);
    }
}
