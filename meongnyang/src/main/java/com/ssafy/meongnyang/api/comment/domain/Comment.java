package com.ssafy.meongnyang.api.comment.domain;

import com.ssafy.meongnyang.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Comment extends BaseTimeEntity {
    private Long id;
    private Long boardId;
    private Long userId;
    private String content;

    @Builder
    public Comment(Long boardId, Long userId, String content) {
        this.boardId = boardId;
        this.userId = userId;
        this.content = content;
    }
}
