package com.smartbiz.service.ai;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessInsightsServiceImpl
        implements BusinessInsightsService {

    private final OpenAiService claudeAI;

    private static final String SYSTEM = """
            You are a smart business assistant.
            Answer questions using only the provided data.
            Rules:
            - Be SHORT and DIRECT. Maximum 3-4 sentences.
            - Give specific numbers and names from the data.
            - Never explain what you cannot do. If data is insufficient, say so in one sentence.
            - No bullet lists unless asked. Plain sentences only.
            """;

    @Override
    public String analyzeBusinessData(
            String dataJson, String question) {

        String msg = """
                Data: %s
                Question: %s
                """.formatted(dataJson, question);

        return claudeAI.chat(SYSTEM, msg);
    }
}
