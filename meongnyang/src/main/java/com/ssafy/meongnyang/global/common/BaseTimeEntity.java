package com.ssafy.meongnyang.global.common;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public abstract class BaseTimeEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
