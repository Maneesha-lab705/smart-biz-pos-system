//package com.smartbiz.service.ai;
//
//import com.smartbiz.dto.ai.AiRequest;
//import com.smartbiz.dto.ai.AiResponse;
//import com.smartbiz.repository.SaleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class BusinessInsightService {
//
//    private final OpenAiService openAiService;
//    private final SaleRepository saleRepository;
//
//    private static final String INSIGHT_SYSTEM =
//        "You are SmartBiz AI, an intelligent business assistant for small and medium businesses. " +
//        "Help business owners understand their sales, profits, and performance trends. " +
//        "Be concise, friendly, and provide actionable insights. Reply in the same language the user wrote in.";
//
//    private static final String EMAIL_SYSTEM =
//        "You are a professional business email writer for small businesses. " +
//        "Write polite, clear, and professional emails. " +
//        "Format with: Subject:, Body:, and Sign-off. Reply in the same language the user wrote in.";
//
//    private static final String MARKETING_SYSTEM =
//        "You are a creative social media marketing expert for small businesses. " +
//        "Write engaging, fun, and professional posts for Facebook, Instagram, or WhatsApp. " +
//        "Include emojis where appropriate. Keep it short and punchy. " +
//        "Reply in the same language the user wrote in.";
//
//    /** Natural language business insight - e.g. "How did I perform last month?" */
//    public AiResponse generateInsight(AiRequest request) {
//        LocalDateTime from = LocalDateTime.now().minusMonths(1);
//        LocalDateTime to   = LocalDateTime.now();
//        BigDecimal totalSales = saleRepository.getTotalRevenueByBusinessAndDateRange(
//                request.getBusinessId(), from, to);
//
//        String context = String.format(
//            "Business ID: %d | Total revenue in the last 30 days: LKR %.2f. User question: %s",
//            request.getBusinessId(),
//            totalSales != null ? totalSales : BigDecimal.ZERO,
//            request.getPrompt()
//        );
//
//        return AiResponse.builder()
//                .result(openAiService.chat(INSIGHT_SYSTEM, context))
//                .type("REPORT")
//                .build();
//    }
//
//    /** AI email generator for customer follow-ups or complaints */
//    public AiResponse generateEmail(AiRequest request) {
//        return AiResponse.builder()
//                .result(openAiService.chat(EMAIL_SYSTEM, request.getPrompt()))
//                .type("EMAIL")
//                .build();
//    }
//
//    /** Marketing post writer - e.g. "Write a Facebook post for new arrivals" */
//    public AiResponse generateMarketingPost(AiRequest request) {
//        return AiResponse.builder()
//                .result(openAiService.chat(MARKETING_SYSTEM, request.getPrompt()))
//                .type("MARKETING")
//                .build();
//    }
//}
