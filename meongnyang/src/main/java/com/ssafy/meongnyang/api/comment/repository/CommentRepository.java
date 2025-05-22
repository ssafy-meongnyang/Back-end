package com.ssafy.meongnyang.api.comment.repository;

import com.ssafy.meongnyang.api.comment.domain.Comment;
import com.ssafy.meongnyang.api.comment.dto.response.CommentGetResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {
    void createComment(Comment comment);
    List<CommentGetResponse> getComment(Long boardId);
    void updateComment(Comment comment);
    Comment getCommentById(Long commentId);
    void deleteComment(Long commentId);
}
