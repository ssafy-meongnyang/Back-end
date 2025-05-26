package com.ssafy.meongnyang.api.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

public record BoardListGetResponse(
        Long id,
        String category,
        String title,
        String nickname,
        LocalDateTime createdAt
) {
}
