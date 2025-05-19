package com.ssafy.meongnyang.api.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BoardListGetResponse(
        String category,
        String title,
        String nickname,
        LocalDateTime createdAt
) {
}
