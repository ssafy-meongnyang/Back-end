package com.ssafy.meongnyang.api.pet.service;

import com.ssafy.meongnyang.api.pet.domain.Pet;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.dto.request.PetUpdateRequest;
import com.ssafy.meongnyang.api.pet.dto.response.PetDetailResponse;
import com.ssafy.meongnyang.api.pet.dto.response.PetListResponse;
import com.ssafy.meongnyang.api.pet.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    @Override
    public void registerPet(Long userId, PetRequest petRequest) {
        // 1. DTO -> Entity로 변환
        Pet pet = Pet.builder()
                .userId(userId)
                .name(petRequest.name())
                .breed(petRequest.breed())
                .birthDate(petRequest.birthDate())
                .gender(petRequest.gender())
                .weight(petRequest.weight())
                .shape(petRequest.shape())
                .isAllergic(petRequest.isAllergic())
                .profileImageUrl(petRequest.profileImageUrl())
                .isRepresentative(false)
                .build();
        // 2. Insert + PK 자동 세팅
        petRepository.insertPet(pet);
        Long petId = pet.getId();
        // 3. 알러지 등록
        if(petRequest.allergens() != null && !petRequest.allergens().isEmpty()){
            petRepository.insertAllergens(petId,petRequest.allergens());
        }

        // 4. 건강 관심사 등록
        if(petRequest.healthConcerns() != null && !petRequest.healthConcerns().isEmpty()){
            petRepository.insertHealthConcerns(petId,petRequest.healthConcerns());
        }
    }

    @Override
    public List<PetListResponse> getPetListByUserId(Long userId) {
        List<Pet> pets = petRepository.findPetsByUserId(userId);

        return pets.stream().map(pet ->{
            List<String> concerns = petRepository.findHealthConcernsByPetId(pet.getId());
            return new PetListResponse(
                    pet.getId(),
                    pet.getName(),
                    pet.getBreed(),
                    pet.getBirthDate(),
                    pet.getWeight(),
                    pet.getProfileImageUrl(),
                    pet.getIsRepresentative(),

                    concerns
            );
        }).toList();
    }

    @Override
    public PetDetailResponse getPetDetail(Long petId) {
        Pet pet = petRepository.findPetById(petId);
        List<String> allergens = petRepository.findAllergensByPetId(petId);
        List<String> concerns = petRepository.findHealthConcernsByPetId(petId);

        return new PetDetailResponse(
                pet.getId(),
                pet.getName(),
                pet.getBreed(),
                pet.getBirthDate(),
                pet.getGender(),
                pet.getWeight(),
                pet.getShape(),
                pet.getIsAllergic(),
                pet.getProfileImageUrl(),
                pet.getIsRepresentative(),
                allergens,
                concerns
        );
    }

    @Override
    public void updatePetInfo(Long userId, Long petId, PetUpdateRequest petUpdateRequest) {
        // 1. 기존 멍냥이 정보 수정
        Pet pet = Pet.builder()
                .id(petId)
                .userId(userId)
                .name(petUpdateRequest.name())
                .breed(petUpdateRequest.breed())
                .birthDate(petUpdateRequest.birthDate())
                .gender(petUpdateRequest.gender())
                .weight(petUpdateRequest.weight())
                .shape(petUpdateRequest.shape())
                .isAllergic(petUpdateRequest.isAllergic())
                .profileImageUrl(petUpdateRequest.profileImageUrl())
                .build();

        petRepository.updatePet(pet);

        // 2. 기존 알러지 / 건강 관심사 초기화 후 새로 등록 (N:1 관계 갱신 시 삭제 후 재삽입이 가장 단순함)
        petRepository.deleteAllergensByPetId(petId);
        petRepository.deleteHealthConcernsByPetId(petId);

        if (petUpdateRequest.allergens() != null && !petUpdateRequest.allergens().isEmpty()) {
            petRepository.insertAllergens(petId, petUpdateRequest.allergens());
        }

        if (petUpdateRequest.healthConcerns() != null && !petUpdateRequest.healthConcerns().isEmpty()) {
            petRepository.insertHealthConcerns(petId, petUpdateRequest.healthConcerns());
        }
    }

    @Override
    public void deletePetdata(Long userId, Long petId) {
        petRepository.deletePetdata(userId,petId);
    }
}
