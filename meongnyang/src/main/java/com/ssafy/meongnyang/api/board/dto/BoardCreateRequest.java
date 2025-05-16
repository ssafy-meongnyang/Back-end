package com.ssafy.meongnyang.api.board.dto;

import com.ssafy.meongnyang.api.board.domain.Category;
import org.springframework.web.multipart.MultipartFile;

public record BoardCreateRequest(
        String category,
        String title,
        String content,
        MultipartFile image,
        Long userId
) {
}
