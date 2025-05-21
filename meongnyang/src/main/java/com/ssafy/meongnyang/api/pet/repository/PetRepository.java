package com.ssafy.meongnyang.api.pet.repository;

import com.ssafy.meongnyang.api.pet.domain.Pet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetRepository {

    void insertPet(Pet pet);
    void insertAllergens(@Param("petId") Long petId, @Param("allergens") List<String> allergens );
    void insertHealthConcerns(@Param("petId") Long petId, @Param("concerns") List<String> concerns);
    void setRepresentative(@Param("petId") Long petId); // 대표 멍냥이 메서드 추후 추가
    List<Pet> findPetsByUserId(@Param("userId") Long userId);
    List<String> findHealthConcernsByPetId(@Param("petId") Long petId);
    List<String> findAllergensByPetId(@Param("petId") Long petId);
    Pet findPetById(@Param("petId") Long petId);
}
