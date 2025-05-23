package com.ssafy.meongnyang.api.diet.service;

import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface DietService {
    void createDiet(Long userId, DietRequest dietRequest);
}
