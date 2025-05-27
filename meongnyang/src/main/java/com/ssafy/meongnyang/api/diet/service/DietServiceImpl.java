package com.ssafy.meongnyang.api.diet.service;

import com.ssafy.meongnyang.api.diet.domain.Diet;
import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietListResponse;
import com.ssafy.meongnyang.api.diet.dto.request.DietUpdateRequest;
import com.ssafy.meongnyang.api.diet.dto.response.DietResponse;
import com.ssafy.meongnyang.api.diet.repository.DietRepository;
import com.ssafy.meongnyang.global.exception.CustomException;
import com.ssafy.meongnyang.global.external.S3Service;
import com.ssafy.meongnyang.global.response.enums.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DietServiceImpl implements DietService {

    private final DietRepository dietRepository;
    private final S3Service s3Service;

    private static final String BUCKET_PATH = "https://meongnyang-ssafy.s3.ap-northeast-2.amazonaws.com/";
    private static final String DIET_FILE_PATH = "diet_file/";

    @Override
    public void createDiet(Long userId, DietRequest dietRequest) {
        try {
            // 1. 이미지 파일을 S3에 업로드
            String breakfastPath = uploadImageToS3(dietRequest.getBreakfastImgPath());
            String lunchPath = uploadImageToS3(dietRequest.getLunchImgPath());
            String dinnerPath = uploadImageToS3(dietRequest.getDinnerImgPath());
            // 2. DTO → 도메인 객체 변환
            Diet diet = Diet.builder()
                    .userId(userId)
                    .date(dietRequest.getDate())
                    .title(dietRequest.getTitle())
                    .breakfastImgPath(breakfastPath)
                    .breakfastDes(dietRequest.getBreakfastDes())
                    .lunchImgPath(lunchPath)
                    .lunchDes(dietRequest.getLunchDes())
                    .dinnerImgPath(dinnerPath)
                    .dinnerDes(dietRequest.getDinnerDes())
                    .snack(dietRequest.getSnack())
                    .memo(dietRequest.getMemo())
                    .exercise(dietRequest.getExercise())
                    .build();
            System.out.println(diet);
            // 3. DB에 저장
            dietRepository.insertDiet(diet);

        } catch (IOException e) {
            throw new RuntimeException("식단 이미지 업로드 중 오류 발생: " + e.getMessage());
        }
    }

    private String uploadImageToS3(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        return BUCKET_PATH + s3Service.uploadImage(DIET_FILE_PATH, file);
    }

    @Override
    public List<DietResponse> getDietList(Long userId) {
        return dietRepository.selectDietListByUserId(userId);
    }

    @Override
    public DietResponse getDietDetail(Long userId, Long dietId) {
        return dietRepository.selectDietDetail(userId, dietId);
    }

    @Override
    public void updateDiet(Long userId, Long dietId, DietUpdateRequest dietUpdateRequest) {
        try {

            // 1. 기존 데이터 조회
            DietResponse existing = dietRepository.selectDietDetail(userId, dietId);
            if (existing == null) {
                throw new CustomException(ErrorCode.NOT_FOUND_DIET);
            }

            // 2. 새 이미지가 있으면 업로드, 없으면 기존 경로 유지
            String breakfastPath = dietUpdateRequest.getBreakfastImg() != null && !dietUpdateRequest.getBreakfastImg().isEmpty()
                    ? uploadImageToS3(dietUpdateRequest.getBreakfastImg())
                    : existing.breakfastImg();

            String lunchPath = dietUpdateRequest.getLunchImg() != null && !dietUpdateRequest.getLunchImg().isEmpty()
                    ? uploadImageToS3(dietUpdateRequest.getLunchImg())
                    : existing.lunchImg();

            String dinnerPath = dietUpdateRequest.getDinnerImg() != null && !dietUpdateRequest.getDinnerImg().isEmpty()
                    ? uploadImageToS3(dietUpdateRequest.getDinnerImg())
                    : existing.dinnerImg();


            // 3. Diet 객체로 변환
            Diet updated = Diet.builder()
                    .dietId(dietId)
                    .userId(userId)
                    .date(dietUpdateRequest.getDate())
                    .title(dietUpdateRequest.getTitle())
                    .breakfastImgPath(breakfastPath)
                    .breakfastDes(dietUpdateRequest.getBreakfastDes())
                    .lunchImgPath(lunchPath)
                    .lunchDes(dietUpdateRequest.getLunchDes())
                    .dinnerImgPath(dinnerPath)
                    .dinnerDes(dietUpdateRequest.getDinnerDes())
                    .snack(dietUpdateRequest.getSnack())
                    .memo(dietUpdateRequest.getMemo())
                    .exercise(dietUpdateRequest.getExercise())
                    .build();

            // 4. 업데이트 수행
            dietRepository.updateDiet(updated);

        } catch (IOException e) {
            throw new RuntimeException("식단 이미지 업로드 중 오류 발생: " + e.getMessage());
        }
    }

    @Override
    public void deleteDiet(Long userId, Long dietId) {
        dietRepository.deleteDiet(userId,dietId);
    }

}
