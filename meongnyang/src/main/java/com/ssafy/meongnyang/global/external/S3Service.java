package com.ssafy.meongnyang.global.external;

import com.ssafy.meongnyang.global.config.AwsConfig;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Service {

    private final String bucketName;
    private final AwsConfig awsConfig;
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList("image/jpeg", "image/png","image/jpg", "image/webp");

    public S3Service(@Value("${aws.s3.bucket}") final String bucketName, AwsConfig awsConfig) {
        this.bucketName = bucketName;
        this.awsConfig = awsConfig;
    }

    public String uploadImage(String directoryPath, MultipartFile file) throws IOException {
        final String key = directoryPath + genereateImageFileName();
        final S3Client s3Client = awsConfig.getS3Client();

        validateExtension(file);
        validateFileSize(file);

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .contentDisposition("inline")
                .build();

        RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
        s3Client.putObject(request, requestBody);
        return key;
    }

    private String genereateImageFileName(){
        return UUID.randomUUID() + ".jpg";
    }

    private void validateExtension(MultipartFile file) {
        String contentType = file.getContentType();
        if(!IMAGE_EXTENSIONS.contains(contentType)) {
            throw new RuntimeException("지원하는 이미지 확장자가 아닙니다.");
        }
    }

    private static final Long MAX_FILE_SIZE = 5 * 1024 * 1024L;

    private void validateFileSize(MultipartFile file) {
        if(file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("이미지 사이즈는 5MB를 넘을 수 없습니다.");
        }
    }

}
