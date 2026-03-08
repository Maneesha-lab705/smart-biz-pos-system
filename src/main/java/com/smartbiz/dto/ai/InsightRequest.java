package com.smartbiz.dto.ai;

import lombok.Data;

// com/smartbiz/dto/ai/InsightRequest.java
@Data
public class InsightRequest {
    private String question;
    private Object data;  // ඕනෑම JSON structure එකක් accept කරනවා
}