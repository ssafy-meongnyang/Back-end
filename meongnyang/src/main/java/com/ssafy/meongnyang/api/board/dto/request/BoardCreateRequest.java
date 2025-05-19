package com.ssafy.meongnyang.api.board.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record BoardCreateRequest(
        String category,
        String title,
        String content,
        MultipartFile image,
        Long userId
) {
}
