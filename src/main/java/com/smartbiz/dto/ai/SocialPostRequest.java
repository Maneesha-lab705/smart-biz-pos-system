package com.smartbiz.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialPostRequest {
    private String platform;        // "Facebook", "Instagram", "Twitter"
    private String businessName;
    private String campaignType;
    private String offerDetails;
    private String targetAudience;
    private String tone;
}