package com.ssafy.meongnyang.api.board.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record BoardUpdateRequest(
        String category,
        String title,
        String content,
        MultipartFile image,
        boolean isDeleted
) {
}
