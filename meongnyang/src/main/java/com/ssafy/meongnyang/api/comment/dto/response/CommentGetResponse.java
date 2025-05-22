package com.ssafy.meongnyang.api.comment.dto.response;

import java.time.LocalDateTime;

public record CommentGetResponse(
        Long commentId,
        String nickname,
        String content,
        LocalDateTime createdAt
) {

}
