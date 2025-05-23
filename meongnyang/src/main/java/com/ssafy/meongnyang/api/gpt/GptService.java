package com.ssafy.meongnyang.api.gpt;

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

    private final GPTConfig gptConfig;
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.openai.com/v1/chat/completions";

    public String chat(String prompt) {
        Map<String, Object> body = new HashMap<>();
        body.put("model", gptConfig.getModel());
        body.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        Map response = restTemplate.postForObject(apiUrl, entity, Map.class);

        Map firstChoice = (Map) ((List) response.get("choices")).get(0);
        Map message = (Map) firstChoice.get("message");

        return (String) message.get("content");
    }
}
