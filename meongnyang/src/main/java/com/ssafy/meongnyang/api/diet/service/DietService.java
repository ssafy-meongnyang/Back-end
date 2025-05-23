package com.ssafy.meongnyang.api.diet.service;

import com.ssafy.meongnyang.api.diet.domain.Diet;
import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DietService {
    void createDiet(Long userId, DietRequest dietRequest);
    List<Diet> getDietList(Long userId);
    DietResponse getDietDetail(Long userId, Long dietId);
}
