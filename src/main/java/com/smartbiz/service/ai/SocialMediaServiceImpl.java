package com.smartbiz.service.ai;


import com.smartbiz.dto.ai.SocialPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocialMediaServiceImpl
        implements SocialMediaService {

    private final OpenAiService claudeAI;

    private static final String SYSTEM = """
        You are a social media marketing expert.
        Create engaging platform-specific posts.
        Include emojis, hashtags, call-to-action.
        """;

    @Override
    public String generatePost(
            SocialPostRequest request) {

        String msg = """
            Platform: %s
            Business: %s
            Campaign: %s
            Offer: %s
            Audience: %s
            Tone: %s
            """.formatted(
                request.getPlatform(),
                request.getBusinessName(),
                request.getCampaignType(),
                request.getOfferDetails(),
                request.getTargetAudience(),
                request.getTone()
        );

        return claudeAI.chat(SYSTEM, msg);
    }
}