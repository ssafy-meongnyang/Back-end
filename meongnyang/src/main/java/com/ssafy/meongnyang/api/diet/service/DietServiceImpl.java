package com.ssafy.meongnyang.api.diet.service;

import com.ssafy.meongnyang.api.diet.domain.Diet;
import com.ssafy.meongnyang.api.diet.dto.request.DietRequest;
import com.ssafy.meongnyang.api.diet.repository.DietRepository;
import com.ssafy.meongnyang.global.external.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
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
            String breakfastPath = uploadImageToS3(dietRequest.getBreakfastImg());
            String lunchPath = uploadImageToS3(dietRequest.getLunchImg());
            String dinnerPath = uploadImageToS3(dietRequest.getDinnerImg());

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
    public List<Diet> getDietList(Long userId) {
        return dietRepository.selectDietListByUserId(userId);
    }
}
