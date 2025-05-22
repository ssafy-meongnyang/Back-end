package com.ssafy.meongnyang.api.comment.repository;

import com.ssafy.meongnyang.api.comment.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {
    void createComment(Comment comment);
}
