package com.smartbiz.service.ai;


import com.smartbiz.dto.ai.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailComposerServiceImpl
        implements EmailComposerService {

    private final OpenAiService claudeAI;

    private static final String SYSTEM = """
        You are a professional email writer.
        Write clear, polite business emails.
        Include subject, greeting, body, closing.
        """;

    @Override
    public String composeEmail(
            EmailRequest request) {

        String msg = """
            Type: %s
            To: %s (%s)
            Context: %s
            Key Points: %s
            Tone: %s
            """.formatted(
                request.getEmailType(),
                request.getRecipientName(),
                request.getRecipientRole(),
                request.getContext(),
                request.getKeyPoints(),
                request.getTone()
        );

        return claudeAI.chat(SYSTEM, msg);
    }
}