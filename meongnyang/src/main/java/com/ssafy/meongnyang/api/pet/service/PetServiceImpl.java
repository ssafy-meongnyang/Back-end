package com.ssafy.meongnyang.api.pet.service;

import com.ssafy.meongnyang.api.pet.domain.Pet;
import com.ssafy.meongnyang.api.pet.dto.request.PetRequest;
import com.ssafy.meongnyang.api.pet.dto.request.PetUpdateRequest;
import com.ssafy.meongnyang.api.pet.dto.response.PetDetailResponse;
import com.ssafy.meongnyang.api.pet.dto.response.PetListResponse;
import com.ssafy.meongnyang.api.pet.repository.PetRepository;
import com.ssafy.meongnyang.global.external.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    private final S3Service s3Service;

    private static final String BUCKET_PATH = "https://meongnyang-ssafy.s3.ap-northeast-2.amazonaws.com/";
    private static final String DIET_FILE_PATH = "pet_file/";

    private String uploadImageToS3(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        return BUCKET_PATH + s3Service.uploadImage(DIET_FILE_PATH, file);
    }
    @Override
    public void registerPet(Long userId, PetRequest petRequest) {
        // 1. DTO -> Entity로 변환
        Pet pet = null;
        try {
            pet = Pet.builder()
                    .userId(userId)
                    .name(petRequest.getName())
                    .breed(petRequest.getBreed())
                    .birthDate(petRequest.getBirthDate())
                    .gender(petRequest.getGender())
                    .weight(petRequest.getWeight())
                    .shape(petRequest.getShape())
                    .allergic(petRequest.getAllergic())
                    .profileImagePath(uploadImageToS3(petRequest.getProfileImagePath()))
                    .representative(false)
                    .build();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(pet);
        // 2. Insert + PK 자동 세팅
        petRepository.insertPet(pet);
        Long petId = pet.getId();
        // 3. 알러지 등록
        if(petRequest.getAllergens() != null && !petRequest.getAllergens().isEmpty()){
            petRepository.insertAllergens(petId,petRequest.getAllergens());
        }

        // 4. 건강 관심사 등록
        if(petRequest.getHealthConcerns() != null && !petRequest.getHealthConcerns().isEmpty()){
            petRepository.insertHealthConcerns(petId,petRequest.getHealthConcerns());
        }

    }

    @Override
    public List<PetListResponse> getPetListByUserId(Long userId) {
        List<Pet> pets = petRepository.findPetsByUserId(userId);

        return pets.stream().map(pet ->{
            // 관심 건강 조회
            List<String> concerns = petRepository.findHealthConcernsByPetId(pet.getId());
            // 각 멍냥이에 대해 PetListResponse로 변환
            return new PetListResponse(
                    pet.getId(),
                    pet.getName(),
                    pet.getBreed(),
                    pet.getGender(),
                    pet.getBirthDate(),
                    pet.getWeight(),
                    pet.getProfileImagePath(),
                    pet.isRepresentative(),
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
                pet.getAllergic(),
                pet.getProfileImagePath(),
                pet.isRepresentative(),
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
                .allergic(petUpdateRequest.isAllergic())
                .profileImagePath(petUpdateRequest.profileImageUrl())
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
