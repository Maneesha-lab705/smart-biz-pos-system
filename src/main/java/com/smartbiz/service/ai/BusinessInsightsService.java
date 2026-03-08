package com.smartbiz.service.ai;

public interface BusinessInsightsService {

    String analyzeBusinessData(
            String dataJson,
            String question
    );

}