//package com.smartbiz.controller;
//
//import com.smartbiz.dto.ai.AiRequest;
//import com.smartbiz.dto.ai.AiResponse;
//import com.smartbiz.response.ApiResponse;
//import com.smartbiz.service.ai.BusinessInsightService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/ai")
//@RequiredArgsConstructor
//public class AiController {
//
//    private final BusinessInsightService insightService;
//
//    /**
//     * POST /api/ai/insight
//     * Body: { "prompt": "How did I perform last month?", "businessId": 1 }
//     */
//    @PostMapping("/insight")
//    public ResponseEntity<ApiResponse<AiResponse>> getInsight(@RequestBody AiRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(insightService.generateInsight(request)));
//    }
//
//    /**
//     * POST /api/ai/email
//     * Body: { "prompt": "Write a follow-up email to a customer who hasn't paid", "businessId": 1 }
//     */
//    @PostMapping("/email")
//    public ResponseEntity<ApiResponse<AiResponse>> generateEmail(@RequestBody AiRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(insightService.generateEmail(request)));
//    }
//
//    /**
//     * POST /api/ai/marketing
//     * Body: { "prompt": "Write a Facebook post for our new arrivals of summer clothes", "businessId": 1 }
//     */
//    @PostMapping("/marketing")
//    public ResponseEntity<ApiResponse<AiResponse>> generateMarketingPost(@RequestBody AiRequest request) {
//        return ResponseEntity.ok(ApiResponse.success(insightService.generateMarketingPost(request)));
//    }
//}
