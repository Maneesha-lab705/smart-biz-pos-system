package com.smartbiz.controller;

import com.smartbiz.dto.ai.AiResponse;
import com.smartbiz.dto.ai.EmailRequest;
import com.smartbiz.dto.ai.InsightRequest;
import com.smartbiz.dto.ai.SocialPostRequest;
import com.smartbiz.service.ai.BusinessInsightsService;
import com.smartbiz.service.ai.EmailComposerService;
import com.smartbiz.service.ai.InvoiceSummaryService;
import com.smartbiz.service.ai.SocialMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AiController {

    private final BusinessInsightsService insightsService;
    private final EmailComposerService emailService;
    private final InvoiceSummaryService invoiceService;
    private final SocialMediaService socialService;

    // 1. Business Insights
    @PostMapping("/insights")
    public ResponseEntity<AiResponse> getInsights(
            @RequestBody InsightRequest request) {
        String result = insightsService.analyzeBusinessData(
                request.getData().toString(),
                request.getQuestion()
        );
        return ResponseEntity.ok(new AiResponse(true, result, null));
    }

    // 2. Email Composer
    @PostMapping("/compose-email")
    public ResponseEntity<AiResponse> composeEmail(
            @RequestBody EmailRequest request) {
        String result = emailService.composeEmail(request);
        return ResponseEntity.ok(new AiResponse(true, result, null));
    }

    // 3. Invoice Summary
    @PostMapping("/invoice-summary")
    public ResponseEntity<AiResponse> summarizeInvoice(
            @RequestBody String invoiceData) {
        String result = invoiceService.summarizeInvoice(invoiceData);
        return ResponseEntity.ok(new AiResponse(true, result, null));
    }

    // 4. Social Media Post
    @PostMapping("/social-post")
    public ResponseEntity<AiResponse> generateSocialPost(
            @RequestBody SocialPostRequest request) {
        String result = socialService.generatePost(request);
        return ResponseEntity.ok(new AiResponse(true, result, null));
    }
}