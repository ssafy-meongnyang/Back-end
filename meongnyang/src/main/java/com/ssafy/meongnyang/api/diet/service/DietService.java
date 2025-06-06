package com.ssafy.meongnyang.api.diet.service;

import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietListResponse;
import com.ssafy.meongnyang.api.diet.dto.request.DietUpdateRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface DietService {
    void createDiet(Long userId, DietRequest dietRequest);
    List<DietResponse> getDietList(Long userId);
    DietResponse getDietDetail(Long userId, Long dietId);
    void updateDiet(Long userId, Long dietId, DietUpdateRequest dietUpdateRequest);
    void deleteDiet(Long userId, Long dietId);
}
