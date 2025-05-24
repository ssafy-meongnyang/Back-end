package com.ssafy.meongnyang.api.gpt;

import com.ssafy.meongnyang.api.pet.domain.Pet;
import com.ssafy.meongnyang.api.pet.dto.response.PetListResponse;
import com.ssafy.meongnyang.api.pet.service.PetServiceImpl;
import com.ssafy.meongnyang.global.config.GPTConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GptService {

    private final PetServiceImpl petServiceImpl;
    private final GPTConfig gptConfig;
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    public String chat(String prompt, Long userId) {
        String command = "너는 반려동물의 식단에 대한 정보를 알려주는 반려동물 식단 전문가야. "
                + "사용자의 요청에 맞게 도움을 주도록 해. "
                + "그리고 유저가 가진 반려동물 정보를 바탕으로 식단을 맞춤형으로 추천해주는거야.";
        String petInfo = convertPetListToPromptString(petServiceImpl.getPetListByUserId(userId));
        Map<String, Object> body = new HashMap<>();
        body.put("model", gptConfig.getModel());
        body.put("messages", List.of(Map.of("role", "user", "content",command + petInfo + prompt)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        Map response = restTemplate.postForObject(apiUrl, entity, Map.class);

        Map firstChoice = (Map) ((List) response.get("choices")).get(0);
        Map message = (Map) firstChoice.get("message");

        return (String) message.get("content");
    }

    public String convertPetListToPromptString(List<PetListResponse> petList) {
        StringBuilder sb = new StringBuilder();
        sb.append("사용자가 가진 반려동물 목록입니다:\n");

        for (PetListResponse pet : petList) {
            sb.append("- 이름: ").append(pet.name()).append("\n");
            sb.append("  품종: ").append(pet.breed()).append("\n");
            sb.append("  생일: ").append(pet.birthDate()).append("\n");
            sb.append("  몸무게: ").append(pet.weight()).append("kg\n");
            sb.append("  대표 반려동물 여부: ").append(pet.representative() ? "예" : "아니오").append("\n");

            List<String> concerns = pet.healthConcerns();
            if (concerns != null && !concerns.isEmpty()) {
                sb.append("  건강 고민: ");
                sb.append(String.join(", ", concerns));
                sb.append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
