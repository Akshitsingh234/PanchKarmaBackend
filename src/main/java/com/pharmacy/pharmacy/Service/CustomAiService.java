package com.pharmacy.pharmacy.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class CustomAiService {

    @Value("${custom.ai.api-key}")
    private String apiKey;

    @Value("${custom.ai.endpoint}")
    private String apiEndpoint;

    private final RestTemplate restTemplate = new RestTemplate();

    public String predict(String symptoms) {
        String prompt = "A patient reports the following symptoms: " + symptoms +
                ". Suggest possible medical conditions (general advice only).";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // OpenRouter expects messages and model
        Map<String, Object> body = Map.of(
                "model", "openai/gpt-4o",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                ),
                "max_tokens", 500  // ✅ well under your 4000 limit
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // Use apiEndpoint from properties
        ResponseEntity<Map> response = restTemplate.postForEntity(apiEndpoint, request, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            // OpenRouter returns choices[0].message.content
            Map choice0 = ((java.util.List<Map>) responseBody.get("choices")).get(0);
            Map message = (Map) choice0.get("message");
            return (String) message.get("content");
        }

        return "Error: No response from AI service.";
    }
}
