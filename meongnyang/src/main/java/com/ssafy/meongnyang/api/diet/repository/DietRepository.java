package com.ssafy.meongnyang.api.diet.repository;

import com.ssafy.meongnyang.api.diet.domain.Diet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DietRepository {
    void insertDiet(Diet diet);
}
