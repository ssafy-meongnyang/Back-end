package com.ssafy.meongnyang.api.diet.repository;

import com.ssafy.meongnyang.api.diet.domain.Diet;
import com.ssafy.meongnyang.api.diet.dto.response.DietListResponse;
import com.ssafy.meongnyang.api.diet.dto.request.DietUpdateRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DietRepository {
    void insertDiet(Diet diet);
    List<DietResponse> selectDietListByUserId(Long userId);
    DietResponse selectDietDetail(@Param("userId") Long userId, @Param("dietId") Long dietId);
    void updateDiet(Diet updated);
    void deleteDiet(@Param("userId") Long userId, @Param("dietId") Long dietId);
}
