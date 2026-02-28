//package com.smartbiz.service.ai;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class OpenAiService {
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    @Value("${openai.api.url}")
//    private String apiUrl;
//
//    @Value("${openai.api.model}")
//    private String model;
//
//    @Value("${openai.api.max-tokens}")
//    private int maxTokens;
//
//    private final RestTemplate restTemplate;
//    private final ObjectMapper objectMapper;
//
//    /**
//     * Send a prompt to OpenAI and return the text response.
//     */
//    public String chat(String systemPrompt, String userMessage) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("model", model);
//        body.put("max_tokens", maxTokens);
//        body.put("messages", List.of(
//                Map.of("role", "system", "content", systemPrompt),
//                Map.of("role", "user", "content", userMessage)
//        ));
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
//            JsonNode root = objectMapper.readTree(response.getBody());
//            return root.path("choices").get(0).path("message").path("content").asText();
//        } catch (Exception e) {
//            throw new RuntimeException("OpenAI API call failed: " + e.getMessage());
//        }
//    }
//}
