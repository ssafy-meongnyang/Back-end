package com.ssafy.meongnyang.api.pet.service;

import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.dto.request.PetUpdateRequest;
import com.ssafy.meongnyang.api.pet.dto.response.PetDetailResponse;
import com.ssafy.meongnyang.api.pet.dto.response.PetListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PetService {
    void registerPet(Long userId, PetRequest petRequest);
    List<PetListResponse> getPetListByUserId(Long userId);
    PetDetailResponse getPetDetail(Long petId);
    void updatePetInfo(Long userId, Long petId, PetUpdateRequest petUpdateRequest);
    void deletePetdata(Long userId, Long petId);
}
