package com.smartbiz.service.ai;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessInsightsServiceImpl
        implements BusinessInsightsService {

    private final OpenAiService claudeAI;

    private static final String SYSTEM = """
            You are a business analyst.
            Analyze data and provide clear insights.
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
