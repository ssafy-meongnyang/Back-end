package com.ssafy.meongnyang.api.board.domain;

import com.ssafy.meongnyang.global.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Board extends BaseTimeEntity {
    private Long id;
    private String category;
    private Long userId;
    private String title;
    private String content;
    private String imageUrl;

    @Builder
    public Board(String category, Long userId, String title, String content, String imageUrl) {
        this.category = category;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
