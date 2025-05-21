package com.ssafy.meongnyang.api.pet.service;

import com.ssafy.meongnyang.api.pet.domain.Pet;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
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
}
