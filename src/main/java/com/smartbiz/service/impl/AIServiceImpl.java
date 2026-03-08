//package com.smartbiz.service.impl;
//
//import com.smartbiz.service.ai.AIService;
//import com.smartbiz.service.ai.OpenAiService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AIServiceImpl implements AIService {
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    private String callAI(String systemPrompt, String userMessage) {
//        OpenAiService service = new OpenAiService(apiKey);
//
//        ChatMessage systemMsg = new ChatMessage("system", systemPrompt);
//        ChatMessage userMsg = new ChatMessage("user", userMessage);
//
//        ChatCompletionRequest request = ChatCompletionRequest.builder()
//                .model("gpt-4o-mini")
//                .messages(List.of(systemMsg, userMsg))
//                .maxTokens(1000)
//                .build();
//
//        return service.createChatCompletion(request)
//                .getChoices()
//                .get(0)
//                .getMessage()
//                .getContent();
//    }
//
//    @Override
//    public AIResponseDTO generateBusinessInsight(String question) {
//        String systemPrompt = "You are a smart business analyst for a Sri Lankan small business.";
//        return new AIResponseDTO(callAI(systemPrompt, question), "BUSINESS_INSIGHT");
//    }
//
//    @Override
//    public AIResponseDTO composeEmail(String instruction) {
//        String systemPrompt = "You are a professional email writer. Write clear and polite emails. Start with Subject line.";
//        return new AIResponseDTO(callAI(systemPrompt, instruction), "EMAIL");
//    }
//
//    @Override
//    public AIResponseDTO summarizeInvoice(String invoiceDetails) {
//        String systemPrompt = "You are a financial assistant. Explain invoices in simple terms.";
//        return new AIResponseDTO(callAI(systemPrompt, invoiceDetails), "INVOICE_SUMMARY");
//    }
//
//    @Override
//    public AIResponseDTO generateSocialMediaPost(String instruction) {
//        String systemPrompt = "You are a creative social media content creator. Create engaging posts with emojis and hashtags.";
//        return new AIResponseDTO(callAI(systemPrompt, instruction), "SOCIAL_MEDIA_POST");
//    }
//}