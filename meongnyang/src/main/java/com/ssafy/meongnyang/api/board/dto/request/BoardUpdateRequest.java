package com.ssafy.meongnyang.api.board.dto.request;

import jakarta.annotation.Nullable;
import org.springframework.web.multipart.MultipartFile;

public record BoardUpdateRequest(
        String category,
        String title,
        String content,
        @Nullable MultipartFile image,
        boolean isDeleted
) {
}
