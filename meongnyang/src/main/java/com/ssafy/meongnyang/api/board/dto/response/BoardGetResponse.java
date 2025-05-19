package com.ssafy.meongnyang.api.board.dto.response;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record BoardGetResponse(
        String category,
        String title,
        String nickname,
        String profileImageUrl,
        LocalDate date,
        String content,
        String imageUrl
) {
}
